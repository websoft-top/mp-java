package com.gxwebsoft.common.system.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.security.JwtSubject;
import com.gxwebsoft.common.core.security.JwtUtil;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.utils.RequestUtil;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.system.entity.*;
import com.gxwebsoft.common.system.param.UserParam;
import com.gxwebsoft.common.system.result.LoginResult;
import com.gxwebsoft.common.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.gxwebsoft.common.core.constants.PlatformConstants.MP_WEIXIN;
import static com.gxwebsoft.common.core.constants.RedisConstants.ACCESS_TOKEN_KEY;

@RestController
@RequestMapping("/api/wx-login")
@Api(tags = "微信小程序登录API")
public class WxLoginController extends BaseController {
    private final StringRedisTemplate redisTemplate;
    @Resource
    private SettingService settingService;
    @Resource
    private UserService userService;
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private LoginRecordService loginRecordService;
    @Resource
    private RoleService roleService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RequestUtil requestUtil;
    @Resource
    private ConfigProperties config;
    @Resource
    private UserRefereeService userRefereeService;

    public WxLoginController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation("获取微信AccessToken")
    @Transactional(rollbackFor = {Exception.class})
    @PostMapping("/getAccessToken")
    public ApiResult<String> getMpAccessToken() {
        return success("操作成功", getAccessToken());
    }

    @ApiOperation("获取微信openId")
    @Transactional(rollbackFor = {Exception.class})
    @PostMapping("/getOpenId")
    public ApiResult<LoginResult> getOpenId(@RequestBody UserParam userParam, HttpServletRequest request) {
        // 1.获取openid
        JSONObject result = getOpenIdByCode(userParam);
        String openid = result.getString("openid");
        String unionid = result.getString("unionid");
        if (openid == null) {
            return fail("获取openid失败", null);
        }
        // 2.通过openid查询用户是否已存在
        User user = userService.getByOauthId(userParam);
        // 3.存在则签发token并返回登录成功,不存在则注册新用户
        if (user == null) {
            user = addUser(userParam);
        }
        // 4.签发token
        loginRecordService.saveAsync(user.getUsername(), LoginRecord.TYPE_LOGIN, null, user.getTenantId(), request);
        String access_token = JwtUtil.buildToken(new JwtSubject(user.getUsername(), user.getTenantId()),
                configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        return success("登录成功", new LoginResult(access_token, user));
    }

    @ApiOperation("微信授权手机号码并登录")
    @Transactional(rollbackFor = {Exception.class})
    @PostMapping("/loginByMpWxPhone")
    public ApiResult<LoginResult> loginByMpWxPhone(@RequestBody UserParam userParam, HttpServletRequest request) {
        // 获取手机号码
        String phone = getPhoneByCode(userParam);
        if (phone == null) {
            String key = ACCESS_TOKEN_KEY.concat(":").concat(getTenantId().toString());
            redisTemplate.delete(key);
            throw new BusinessException("授权失败，请重试");
        }
        // 查询是否存在
        User user = userService.getByPhone(phone);
        // 不存在则注册
        if (user == null) {
            if ((userParam.getOpenid() == null || userParam.getOpenid().isEmpty()) && userParam.getAuthCode() != null) {
                UserParam userParam2 = new UserParam();
                userParam2.setCode(userParam.getAuthCode());
                JSONObject result = getOpenIdByCode(userParam2);
                System.out.println("userInfo res:" + result);
                String openid = result.getString("openid");
//                String unionid = result.getString("unionid");
                userParam.setOpenid(openid);
            }
            userParam.setPhone(phone);
            user = addUser(userParam);
            user.setRecommend(1);
        }else {
            // 存在则检查绑定上级
            if (userParam.getSceneType() != null && userParam.getSceneType().equals("save_referee") && userParam.getRefereeId() != null && userParam.getRefereeId() != 0) {
            UserReferee check = userRefereeService.check(user.getUserId(), userParam.getRefereeId());
            if (check == null) {
                UserReferee userReferee = new UserReferee();
                userReferee.setDealerId(userParam.getRefereeId());
                userReferee.setUserId(user.getUserId());
                userRefereeService.save(userReferee);
            }
        }
        }
        // 签发token
        String access_token = JwtUtil.buildToken(new JwtSubject(user.getUsername(), user.getTenantId()),
                configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        loginRecordService.saveAsync(user.getUsername(), LoginRecord.TYPE_REGISTER, null, user.getTenantId(), request);
        // 附加体育中心项目用户信息
//    user.setBookingUser();
        return success("登录成功", new LoginResult(access_token, user));
    }

    @ApiOperation("微信授权手机号码并更新")
    @Transactional(rollbackFor = {Exception.class})
    @PostMapping("/updatePhoneByMpWx")
    public ApiResult<?> updatePhoneByMpWx(@RequestBody UserParam userParam) {
        // 获取微信授权手机号
        String phone = getPhoneByCode(userParam);
        // 查询当前用户
        User user = userService.getById(userParam.getUserId());
        if (user != null && phone != null) {
            user.setPhone(phone);
            userService.updateUser(user);
            return success("更新成功", phone);
        }
        return fail("更新失败");
    }

    /**
     * 新用户注册
     */
    private User addUser(UserParam userParam) {
        User addUser = new User();
        // 注册用户
        addUser.setStatus(0);
        addUser.setUsername(createUsername("wx_"));
        addUser.setNickname("微信用户");
        addUser.setPlatform(MP_WEIXIN);
        addUser.setGradeId(2);
        if (userParam.getGradeId() != null) {
            addUser.setGradeId(userParam.getGradeId());
        }
        if (userParam.getPhone() != null) {
            addUser.setPhone(userParam.getPhone());
        }
        if (StrUtil.isNotBlank(userParam.getOpenid())) {
            addUser.setOpenid(userParam.getOpenid());
        }
        if (StrUtil.isNotBlank(userParam.getUnionid())) {
            addUser.setUnionid(userParam.getUnionid());
        }
        addUser.setPassword(userService.encodePassword(CommonUtil.randomUUID16()));
        addUser.setTenantId(getTenantId());
        addUser.setRecommend(1);
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("role_code", "user"), false);
        addUser.setRoleId(role.getRoleId());
        if (userService.saveUser(addUser)) {
            // 添加用户角色
            final UserRole userRole = new UserRole();
            userRole.setUserId(addUser.getUserId());
            userRole.setTenantId(addUser.getTenantId());
            userRole.setRoleId(addUser.getRoleId());
            userRoleService.save(userRole);
        }
        // 绑定关系
        if (userParam.getSceneType() != null && userParam.getSceneType().equals("save_referee") && userParam.getRefereeId() != null && userParam.getRefereeId() != 0) {
            UserReferee check = userRefereeService.check(addUser.getUserId(), userParam.getRefereeId());
            if (check == null) {
                UserReferee userReferee = new UserReferee();
                userReferee.setDealerId(userParam.getRefereeId());
                userReferee.setUserId(addUser.getUserId());
                userRefereeService.save(userReferee);
            }
        }
        return addUser;
    }

    // 获取openid
    private JSONObject getOpenIdByCode(UserParam userParam) {
        // 获取微信小程序配置信息
        JSONObject setting = settingService.getBySettingKey("mp-weixin");
        // 获取openId
        String apiUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + setting.getString("appId") + "&secret=" + setting.getString("appSecret") + "&js_code=" + userParam.getCode() + "&grant_type=authorization_code";
        // 执行get请求
        String result = HttpUtil.get(apiUrl);
        // 解析access_token
        return JSON.parseObject(result);
    }

    /**
     * 获取微信手机号码
     *
     * @param userParam 需要传微信凭证code
     */
    private String getPhoneByCode(UserParam userParam) {
        // 获取手机号码
        String apiUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + getAccessToken();
        HashMap<String, Object> paramMap = new HashMap<>();
        if (StrUtil.isBlank(userParam.getCode())) {
            throw new BusinessException("code不能为空");
        }
        paramMap.put("code", userParam.getCode());
        // 执行post请求
        String post = HttpUtil.post(apiUrl, JSON.toJSONString(paramMap));
        JSONObject json = JSON.parseObject(post);
        if (json.get("errcode").equals(0)) {
            JSONObject phoneInfo = JSON.parseObject(json.getString("phone_info"));
            // 微信用户的手机号码
            final String phoneNumber = phoneInfo.getString("phoneNumber");
            // 验证手机号码
//            if (userParam.getNotVerifyPhone() == null && !Validator.isMobile(phoneNumber)) {
//                String key = ACCESS_TOKEN_KEY.concat(":").concat(getTenantId().toString());
//                redisTemplate.delete(key);
//                throw new BusinessException("手机号码格式不正确");
//            }
            return phoneNumber;
        }
        return null;
    }

    /**
     * 生成随机账号
     *
     * @return username
     */
    private String createUsername(String type) {
        return type.concat(RandomUtil.randomString(12));
    }

    /**
     * 获取接口调用凭据AccessToken
     * <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-access-token/getAccessToken.html">...</a>
     */
    private String getAccessToken() {
        String key = ACCESS_TOKEN_KEY.concat(":").concat(getTenantId().toString());
        // 获取微信小程序配置信息
        JSONObject setting = settingService.getBySettingKey("mp-weixin");
        if (setting == null) {
            throw new BusinessException("请先配置小程序");
        }
        // 从缓存获取access_token
        String value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            // 解析access_token
            JSONObject response = JSON.parseObject(value);
//      return response.getString("access_token");
        }
        // 微信获取凭证接口
        String apiUrl = "https://api.weixin.qq.com/cgi-bin/token";
        // 组装url参数
        String url = apiUrl.concat("?grant_type=client_credential").concat("&appid=").concat(setting.getString("appId")).concat("&secret=").concat(setting.getString("appSecret"));
        // 执行get请求
        String result = HttpUtil.get(url);
        System.out.println("result = " + result);
        // 解析access_token
        JSONObject response = JSON.parseObject(result);
        if (response.getString("access_token") != null) {
            // 存入缓存
            redisTemplate.opsForValue().set(key, result, 7000L, TimeUnit.SECONDS);
            return response.getString("access_token");
        }
        throw new BusinessException("小程序配置不正确");
    }

    @ApiOperation("获取微信openId并更新")
    @PostMapping("/getWxOpenId")
    public ApiResult<?> getWxOpenId(@RequestBody UserParam userParam) {
        final User loginUser = getLoginUser();
        if (loginUser == null) {
            return fail("请先登录");
        }
        // 已存在直接返回
        if (StrUtil.isNotBlank(loginUser.getOpenid())) {
            return success(loginUser);
        }
        // 请求微信接口获取openid
        String apiUrl = "https://api.weixin.qq.com/sns/jscode2session";
        final HashMap<String, Object> map = new HashMap<>();
        final JSONObject setting = settingService.getBySettingKey("mp-weixin");
        final String appId = setting.getString("appId");
        final String appSecret = setting.getString("appSecret");
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", userParam.getCode());
        map.put("grant_type", "authorization_code");
        final String response = HttpUtil.get(apiUrl, map);
        final JSONObject jsonObject = JSONObject.parseObject(response);
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        String unionid = jsonObject.getString("unionid");
        // 保存openID
        if (loginUser.getOpenid() == null || StrUtil.isBlank(loginUser.getOpenid())) {
            loginUser.setOpenid(openid);
            loginUser.setUnionid(unionid);
            requestUtil.updateUser(loginUser);
//            userService.updateById(loginUser);
        }
        return success("获取成功", jsonObject);
    }

    @ApiOperation("仅获取微信openId")
    @PostMapping("/getWxOpenIdOnly")
    public ApiResult<?> getWxOpenIdOnly(@RequestBody UserParam userParam) {

        String apiUrl = "https://api.weixin.qq.com/sns/jscode2session";
        final HashMap<String, Object> map = new HashMap<>();
        final JSONObject setting = settingService.getBySettingKey("mp-weixin");
        final String appId = setting.getString("appId");
        final String appSecret = setting.getString("appSecret");
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", userParam.getCode());
        map.put("grant_type", "authorization_code");
        final String response = HttpUtil.get(apiUrl, map);
        final JSONObject jsonObject = JSONObject.parseObject(response);
        return success("获取成功", jsonObject);
    }

    @ApiOperation("获取微信小程序码-用户ID")
    @GetMapping("/getUserQRCode")
    public ApiResult<?> getQRCode() {
        String apiUrl = "https://api.weixin.qq.com/wxa/getwxacode?access_token=" + getAccessToken();
        final HashMap<String, Object> map = new HashMap<>();
        map.put("path", "/package/user/qrcode?user_id=" + getLoginUserId());
//    map.put("env_version","trial");
        // 获取图片 Buffer
        byte[] qrCode = HttpRequest.post(apiUrl)
                .body(JSON.toJSONString(map))
                .execute().bodyBytes();

        // 保存的文件名称
        final String fileName = CommonUtil.randomUUID8().concat(".png");
        // 保存路径
        String filePath = getUploadDir().concat("qrcode/") + fileName;
        File file = FileUtil.writeBytes(qrCode, filePath);
        if (file != null) {
            return success(config.getFileServer().concat("/qrcode/").concat(fileName));
        }
        return fail("获取失败", null);
    }

    @ApiOperation("获取微信小程序码-订单核销码")
    @GetMapping("/getOrderQRCode/{orderNo}")
    public ApiResult<?> getOrderQRCode(@PathVariable("orderNo") String orderNo) {
        String apiUrl = "https://api.weixin.qq.com/wxa/getwxacode?access_token=" + getAccessToken();
        final HashMap<String, Object> map = new HashMap<>();
        map.put("path", "/package/admin/order-scan?orderNo=".concat(orderNo));
        map.put("env_version", "trial");
        // 获取图片 Buffer
        byte[] qrCode = HttpRequest.post(apiUrl)
                .body(JSON.toJSONString(map))
                .execute().bodyBytes();

        // 保存的文件名称
        final String fileName = CommonUtil.randomUUID8().concat(".png");
        // 保存路径
        String filePath = getUploadDir().concat("qrcode/") + fileName;
        File file = FileUtil.writeBytes(qrCode, filePath);
        if (file != null) {
            return success(config.getFileServer().concat("/qrcode/").concat(fileName));
        }
        return fail("获取失败", null);
    }

    @ApiOperation("获取微信小程序码-订单核销码-数量极多的业务场景")
    @GetMapping("/getOrderQRCodeUnlimited/{orderNo}")
    public ApiResult<?> getOrderQRCodeUnlimited(@PathVariable("orderNo") String orderNo) {
        String apiUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + getAccessToken();
        final HashMap<String, Object> map = new HashMap<>();
        map.put("scene", "orderNo=".concat(orderNo));
        map.put("page", "package/admin/order-scan");
        map.put("env_version", "trial");
        // 获取图片 Buffer
        byte[] qrCode = HttpRequest.post(apiUrl)
                .body(JSON.toJSONString(map))
                .execute().bodyBytes();
        System.out.println("qrCode = " + qrCode);

        // 保存的文件名称
        final String fileName = CommonUtil.randomUUID8().concat(".png");
        // 保存路径
        String filePath = getUploadDir().concat("qrcode/") + fileName;
        File file = FileUtil.writeBytes(qrCode, filePath);
        if (file != null) {
            return success(config.getFileServer().concat("/qrcode/").concat(fileName));
        }
        return fail("获取失败", null);
    }

    /**
     * 文件上传位置(服务器)
     */
    private String getUploadDir() {
        return config.getUploadPath() + "file/";
    }


}
