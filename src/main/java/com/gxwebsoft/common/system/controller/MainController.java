package com.gxwebsoft.common.system.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.security.JwtSubject;
import com.gxwebsoft.common.core.security.JwtUtil;
import com.gxwebsoft.common.core.utils.CacheClient;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.ExistenceParam;
import com.gxwebsoft.common.system.entity.LoginRecord;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.param.LoginParam;
import com.gxwebsoft.common.system.param.SmsCaptchaParam;
import com.gxwebsoft.common.system.param.UpdatePasswordParam;
import com.gxwebsoft.common.system.result.CaptchaResult;
import com.gxwebsoft.common.system.result.LoginResult;
import com.gxwebsoft.common.system.service.*;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 登录认证控制器
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:11
 */
@Api(tags = "登录认证")
@RestController
@RequestMapping("/api")
public class MainController extends BaseController {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private LoginRecordService loginRecordService;
    @Resource
    private CacheClient cacheClient;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation("检查用户是否存在")
    @GetMapping("/existence")
    public ApiResult<?> existence(ExistenceParam<User> param) {
      if (param.isExistence(userService, User::getUserId)) {
        return success("已存在", param.getValue());
      }
      return fail("不存在");
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/auth/user")
    public ApiResult<User> userInfo() {
      final Integer loginUserId = getLoginUserId();
      if(loginUserId != null){
        return success(userService.getByIdRel(getLoginUserId()));
      }
      return fail("loginUserId不存在",null);
    }

    @ApiOperation("获取登录用户菜单")
    @GetMapping("/auth/menu")
    public ApiResult<List<Menu>> userMenu() {
        List<Menu> menus = roleMenuService.listMenuByUserId(getLoginUserId(), Menu.TYPE_MENU);
        return success(CommonUtil.toTreeData(menus, 0, Menu::getParentId, Menu::getMenuId, Menu::setChildren));
    }

    @PreAuthorize("hasAuthority('sys:auth:user')")
    @ApiOperation("修改个人信息")
    @PutMapping("/auth/user")
    public ApiResult<User> updateInfo(@RequestBody User user) {
        user.setUserId(getLoginUserId());
        // 不能修改的字段
        user.setUsername(null);
        user.setPassword(null);
        user.setEmailVerified(null);
        user.setOrganizationId(null);
        user.setStatus(null);
        if (userService.updateById(user)) {
            return success(userService.getByIdRel(user.getUserId()));
        }
        return fail("保存失败", null);
    }

    @PreAuthorize("hasAuthority('sys:auth:password')")
    @ApiOperation("修改自己密码")
    @PutMapping("/auth/password")
    public ApiResult<?> updatePassword(@RequestBody UpdatePasswordParam param) {
        if (StrUtil.hasBlank(param.getOldPassword(), param.getPassword())) {
            return fail("参数不能为空");
        }
        Integer userId = getLoginUserId();
        if (userId == null) {
            return fail("未登录");
        }
        if (!userService.comparePassword(userService.getById(userId).getPassword(), param.getOldPassword())) {
            return fail("原密码输入不正确");
        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(userService.encodePassword(param.getPassword()));
        if (userService.updateById(user)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("图形验证码")
    @GetMapping("/captcha")
    public ApiResult<CaptchaResult> captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        return success(new CaptchaResult(specCaptcha.toBase64(), specCaptcha.text().toLowerCase()));
    }

    @ApiOperation("企业微信登录链接")
    @GetMapping("/wxWorkQrConnect")
    public ApiResult<?> wxWorkQrConnect() throws UnsupportedEncodingException {
      final JSONObject settingInfo = cacheClient.getSettingInfo("wx-work", 10048);
      final String corpId = settingInfo.getString("corpId");
      String encodedReturnUrl = URLEncoder.encode("https://oa.gxwebsoft.com/api/open/wx-work/login","UTF-8");
      String url = "https://open.work.weixin.qq.com/wwopen/sso/3rd_qrConnect?appid=" +corpId+ "&redirect_uri=" +encodedReturnUrl+ "&state=ww_login@gxwebsoft&usertype=admin";
      return success("获取成功",url);
    }

  @ApiOperation("短信验证码")
  @PostMapping("/sendSmsCaptcha")
  public ApiResult<?> sendSmsCaptcha(@RequestBody SmsCaptchaParam param) {
    // 读取短信配置信息
    String string = redisUtil.get("setting:sms:" + getTenantId());
    JSONObject jsonObject = JSONObject.parseObject(string);
    String accessKeyId = jsonObject.getString("accessKeyId");
    String accessKeySecret = jsonObject.getString("accessKeySecret");
    String userTemplateId = jsonObject.getString("userTemplateId");
    String sign = jsonObject.getString("sign");
    if(accessKeyId != null){
      DefaultProfile profile = DefaultProfile.getProfile("regionld", accessKeyId, accessKeySecret);
      IAcsClient client = new DefaultAcsClient(profile);
      CommonRequest request = new CommonRequest();
      request.setSysMethod(MethodType.POST);
      request.setSysDomain("dysmsapi.aliyuncs.com");
      request.setSysVersion("2017-05-25");
      request.setSysAction("SendSms");
      request.putQueryParameter("RegionId", "cn-hangzhou");
      request.putQueryParameter("PhoneNumbers", param.getPhone());
      request.putQueryParameter("SignName", sign);
      request.putQueryParameter("TemplateCode", userTemplateId);
      // 生成短信验证码
      Random randObj = new Random();
      String code = Integer.toString(100000 + randObj.nextInt(900000));
      request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
      try {
        CommonResponse response = client.getCommonResponse(request);
        System.out.println("response = " + response);
        String json = response.getData();
        System.out.println("json = " + json);
        Gson g = new Gson();
        HashMap result = g.fromJson(json, HashMap.class);
        System.out.println("result = " + result);
        if("OK".equals(result.get("Message"))) {
          System.out.println("======================== = " + result);
          cacheClient.set(param.getPhone(),code,5L,TimeUnit.MINUTES);
          String key = "code:" + param.getPhone();
          redisUtil.set(key,code,5L,TimeUnit.MINUTES);
          return success("发送成功",result.get("Message"));
        }else{
          return fail("发送失败");
        }
      } catch (ServerException e) {
        e.printStackTrace();
      } catch (ClientException e) {
        e.printStackTrace();
      }
    }
    return fail("发送失败");
  }

    @ApiOperation("重置密码")
    @PutMapping("/password")
    public ApiResult<?> resetPassword(@RequestBody User user) {
      if (user.getPassword() == null) {
        return fail("参数不正确");
      }
      if (user.getCode() == null) {
        return fail("验证码不能为空");
      }
      // 短信验证码校验
      String code = cacheClient.get(user.getPhone(), String.class);
      if (!StrUtil.equals(code,user.getCode())) {
        return fail("验证码不正确");
      }

      user.setUserId(getLoginUserId());
      user.setPassword(userService.encodePassword(user.getPassword()));
      if (userService.updateById(user)) {
        return success("密码修改成功");
      } else {
        return fail("密码修改失败");
      }
    }

  @ApiOperation("短信验证码登录")
  @PostMapping("/loginBySms")
  public ApiResult<LoginResult> loginBySms(@RequestBody LoginParam param, HttpServletRequest request) {
    final String phone = param.getPhone();
    final Integer tenantId = param.getTenantId();
    final String code = param.getCode();

    User user = userService.getByUsername(phone, tenantId);
    // 验证码校验
    String key = "code:" + param.getPhone();
    if(!code.equals(redisUtil.get(key))){
      String message = "验证码不正确";
      loginRecordService.saveAsync(phone, LoginRecord.TYPE_ERROR, message, tenantId,request);
      return fail(message, null);
    }
    if (user == null) {
      String message = "账号不存在";
      loginRecordService.saveAsync(phone, LoginRecord.TYPE_ERROR, message, tenantId,request);
      return fail(message, null);
    }
    if (!user.getStatus().equals(0)) {
      String message = "账号被冻结";
      loginRecordService.saveAsync(phone, LoginRecord.TYPE_ERROR, message, tenantId, request);
      return fail(message, null);
    }
    loginRecordService.saveAsync(phone, LoginRecord.TYPE_LOGIN, null, tenantId, request);

    // 设置过期时间
    Long tokenExpireTime = configProperties.getTokenExpireTime();
    final JSONObject register = cacheClient.getSettingInfo("register", tenantId);
    if(register != null){
      final String ExpireTime = register.getString("tokenExpireTime");
      if (ExpireTime != null) {
        tokenExpireTime = Long.valueOf(ExpireTime);
      }
    }

    // 签发token
    String access_token = JwtUtil.buildToken(new JwtSubject(phone, tenantId),
      tokenExpireTime, configProperties.getTokenKey());
    return success("登录成功", new LoginResult(access_token, user));
  }

  @ApiOperation("会员注册")
  @PostMapping("/register")
  public ApiResult<LoginResult> register(@RequestBody LoginParam param, HttpServletRequest request) {
    final String phone = param.getPhone();
    final Integer tenantId = param.getTenantId();
    final String code = param.getCode();

    User user = userService.getByUsername(phone, tenantId);
    // 验证码校验
    String key = "code:" + param.getPhone();
    if(!code.equals(redisUtil.get(key))){
      String message = "验证码不正确";
      loginRecordService.saveAsync(phone, LoginRecord.TYPE_ERROR, message, tenantId,request);
      return fail(message, null);
    }
    if (user == null) {
      String message = "账号不存在";
      loginRecordService.saveAsync(phone, LoginRecord.TYPE_ERROR, message, tenantId,request);
      return fail(message, null);
    }
    if (!user.getStatus().equals(0)) {
      String message = "账号被冻结";
      loginRecordService.saveAsync(phone, LoginRecord.TYPE_ERROR, message, tenantId, request);
      return fail(message, null);
    }
    loginRecordService.saveAsync(phone, LoginRecord.TYPE_LOGIN, null, tenantId, request);

    // 设置过期时间
    Long tokenExpireTime = configProperties.getTokenExpireTime();
    final JSONObject register = cacheClient.getSettingInfo("register", tenantId);
    if(register != null){
      final String ExpireTime = register.getString("tokenExpireTime");
      if (ExpireTime != null) {
        tokenExpireTime = Long.valueOf(ExpireTime);
      }
    }

    // 签发token
    String access_token = JwtUtil.buildToken(new JwtSubject(phone, tenantId),
      tokenExpireTime, configProperties.getTokenKey());
    return success("登录成功", new LoginResult(access_token, user));
  }
}
