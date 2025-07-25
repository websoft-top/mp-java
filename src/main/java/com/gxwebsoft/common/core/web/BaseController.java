package com.gxwebsoft.common.core.web;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.core.Constants;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.utils.SignCheckUtil;
import com.gxwebsoft.common.system.entity.User;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller基类
 *
 * @author WebSoft
 * @since 2017-06-10 10:10:19
 */
public class BaseController {
  @Resource
  private HttpServletRequest request;
  @Resource
  private RedisUtil redisUtil;

    /**
     * 获取当前登录的user
     *
     * @return User
     */
    public User getLoginUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object object = authentication.getPrincipal();
                if (object instanceof User) {
                    return (User) object;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     *
     * @return userId
     */
    public Integer getLoginUserId() {
        User loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

    /**
     * 获取当前登录的tenantId
     *
     * @return tenantId
     */
    public Integer getTenantId() {
      String tenantId;
      // 2 从请求头拿ID
      tenantId = request.getHeader("tenantId");
      if(StrUtil.isNotBlank(tenantId)){
        return Integer.valueOf(tenantId);
      }
      // 3 从登录用户拿tenantId
      User loginUser = getLoginUser();
      if (loginUser != null) {
        return loginUser.getTenantId();
      }
      // 1 从域名拿ID
      String Domain = request.getHeader("Domain");
      if (StrUtil.isNotBlank(Domain)) {
        String key = "Domain:" + Domain;
        tenantId = redisUtil.get(key);
        if(tenantId != null){
          System.out.println("从域名拿ID = " + tenantId);
          return Integer.valueOf(tenantId);
        }
      }
      return null;
    }

    /**
     * 返回成功
     *
     * @return ApiResult
     */
    public ApiResult<?> success() {
        return new ApiResult<>(Constants.RESULT_OK_CODE, Constants.RESULT_OK_MSG);
    }

    /**
     * 返回成功
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public ApiResult<?> success(String message) {
        return success().setMessage(message);
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> success(T data) {
        return new ApiResult<>(Constants.RESULT_OK_CODE, Constants.RESULT_OK_MSG, data);
    }

    /**
     * 返回成功
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public <T> ApiResult<T> success(String message, T data) {
        return success(data).setMessage(message);
    }

    /**
     * 返回分页查询数据
     *
     * @param list  当前页数据
     * @param count 总数量
     * @return ApiResult
     */
    public <T> ApiResult<PageResult<T>> success(List<T> list, Long count) {
        return success(new PageResult<>(list, count));
    }

    /**
     * 返回分页查询数据
     *
     * @param iPage IPage
     * @return ApiResult
     */
    public <T> ApiResult<PageResult<T>> success(IPage<T> iPage) {
        return success(iPage.getRecords(), iPage.getTotal());
    }

    /**
     * 返回失败
     *
     * @return ApiResult
     */
    public ApiResult<?> fail() {
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG);
    }

    /**
     * 返回失败
     *
     * @param message 状态信息
     * @return ApiResult
     */
    public ApiResult<?> fail(String message) {
        return fail().setMessage(message);
    }

    /**
     * 返回失败
     *
     * @param data 返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> fail(T data) {
        return fail(Constants.RESULT_ERROR_MSG, data);
    }

    /**
     * 返回失败
     *
     * @param message 状态信息
     * @param data    返回数据
     * @return ApiResult
     */
    public <T> ApiResult<T> fail(String message, T data) {
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, message, data);
    }

    /**
     * 请求参数的空字符串转为null
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    // 自定义函数
    public String getAuthorization(){
      return request.getHeader("Authorization");
    }

    public String getAppId() {
      // 兼容小写
      if(request.getHeader("appid") != null){
        return request.getHeader("appid");
      }
      return request.getHeader("AppId");
    }

    public String getSign() {
      return request.getParameter("sign");
    }

    /**
     * 是否校验签名信息
     * 存在签名信息则需要验证
     */
    public void isCheckSign() {
      if (StrUtil.isNotBlank(getSign())) {
        if(getTenantId() == null){
          throw new BusinessException("签名失败:TenantId不能为空");
        }

        String timestamp1 = request.getParameter("timestamp");
        long timestamp2 = System.currentTimeMillis();
        long time = timestamp2 - Long.parseLong(timestamp1);
        if(time > 600000L){
          throw new BusinessException("签名失败:请求超时");
        }

        Enumeration<String> names = request.getParameterNames();
        //2.遍历正文名称的枚举获得请求参数
        Map<String, String> params = new HashMap<>();
        while(names.hasMoreElements()){
          String name = names.nextElement();
          String value = request.getParameter(name);
          params.put(name,value);
        }
        String signString = SignCheckUtil.getSignString(params, getAppSecret());
        System.out.println("请求的参数 = " + params);
        System.out.println("正确的签名 = " + signString);
        System.out.println("签名是否正确 = " + SignCheckUtil.signCheck(params, getAppSecret()));

        if (!SignCheckUtil.signCheck(params, getAppSecret())) {
          throw new BusinessException("签名失败");
        }
      }

      // 模拟提交参数
      //      String key = "FRbMx1FkG4Qz6GZxY";
      //      Map<String, String> param0 = new HashMap<>();
      //      param0.put("orderId", "D2018062976332656413");
      //      param0.put("MainAccountID", "DC3NHPJ73S");
      //      param0.put("MainAccountSN", "320");
      //      param0.put("payStatus", "2");
      //      param0.put("title","测试");
      //      System.out.println("请求的参数 = " + param0);
      //      String signString0 = SignCheckUtil.getSignString(param0, key);
      //      System.out.println("signString0 = " + signString0);

      //      return SignCheckUtil.signCheck(params, getAppSecret());
    }

    /**
     * 获取当前请求租户的AppSecret
     *
     * @return AppSecret
     */
    public String getAppSecret() {
      String key = "cache5:AppSecret:" + Integer.valueOf(getAppId());
      return redisUtil.get(key);
    }

    /**
     * 根据账号|手机号码|邮箱查找用户ID
     * @return userId
     */
//    public Integer getUserIdByUsername(String username, Integer tenantId){
//      // 按账号搜素
//      User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getTenantId,tenantId));
//      if (user != null && user.getUserId() > 0) {
//        return user.getUserId();
//      }
//      // 按手机号码搜索
//      User userByPhone = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, username).eq(User::getTenantId, tenantId));
//      if (userByPhone != null && userByPhone.getUserId() > 0) {
//        return userByPhone.getUserId();
//      }
//      // 按邮箱搜索
//      User userByEmail = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, username).eq(User::getTenantId, tenantId));
//      if (userByEmail != null && userByEmail.getUserId() > 0) {
//        return userByEmail.getUserId();
//      }
//      throw new BusinessException("找不到该用户");
//    }

    /**
     * 处理方法参数类型转换异常
     * 主要处理URL路径参数中传入"NaN"等无法转换为Integer的情况
     *
     * @param ex 方法参数类型不匹配异常
     * @return ApiResult
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResult<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getName();
        Object value = ex.getValue();
        Class<?> requiredType = ex.getRequiredType();

        // 记录错误日志
        System.err.println("参数类型转换异常: 参数名=" + parameterName +
                          ", 传入值=" + value +
                          ", 期望类型=" + (requiredType != null ? requiredType.getSimpleName() : "unknown"));

        // 如果是ID参数且传入的是"NaN"，返回友好的错误信息
        if ("id".equals(parameterName) && "NaN".equals(String.valueOf(value))) {
            return fail("无效的ID参数，请检查传入的ID值");
        }

        // 其他类型转换错误的通用处理
        return fail("参数格式错误: " + parameterName + " 的值 '" + value + "' 无法转换为 " +
                   (requiredType != null ? requiredType.getSimpleName() : "目标类型"));
    }

}
