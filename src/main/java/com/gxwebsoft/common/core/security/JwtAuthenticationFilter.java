package com.gxwebsoft.common.core.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.core.Constants;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.utils.SignCheckUtil;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理携带token的请求过滤器
 *
 * @author WebSoft
 * @since 2020-03-30 20:48:05
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Resource
  private ConfigProperties configProperties;
  @Value("${spring.profiles.active}")
  String active;
  @Resource
  private RedisUtil redisUtil;
  // 是否读取用户信息
  public static Boolean isReadUserInfo = true;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {
    String access_token = JwtUtil.getAccessToken(request);
    if (StrUtil.isNotBlank(access_token)) {
      try {
        // 解析token
        Claims claims = JwtUtil.parseToken(access_token, configProperties.getTokenKey());
        JwtSubject jwtSubject = JwtUtil.getJwtSubject(claims);

        // 请求主服务器获取用户信息
        if (isReadUserInfo) {
          HashMap<String, Object> map = new HashMap<>();
          map.put("username", jwtSubject.getUsername());
          map.put("tenantId", jwtSubject.getTenantId());
          // 链式构建请求
          String result = HttpRequest.post("https://server.gxwebsoft.com/api/auth/user")
            .header("Authorization", access_token)
            .header("Tenantid", jwtSubject.getTenantId().toString())
            .body(JSONUtil.toJSONString(map))//表单内容
            .timeout(20000)//超时，毫秒
            .execute().body();

          // 校验服务器域名白名单
          final SignCheckUtil checkUtil = new SignCheckUtil();
          String key = "WhiteDomain:" + jwtSubject.getTenantId();
          List<String> whiteDomains = redisUtil.get(key, List.class);
          // 生产环境
          if (active.equals("prod") && !checkUtil.checkWhiteDomains(whiteDomains, request.getServerName())) {
            throw new UsernameNotFoundException("The requested domain name is not on the whitelist");
          }

          JSONObject jsonObject = JSONObject.parseObject(result);
          if(jsonObject.getString("code").equals("401")){
            throw new UsernameNotFoundException("Username not found");
          }
          final String data = jsonObject.getString("data");
          final User user = JSONObject.parseObject(data, User.class);
          List<Menu> authorities = user.getAuthorities().stream()
            .filter(m -> StrUtil.isNotBlank(m.getAuthority())).collect(Collectors.toList());
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user, null, authorities);
          SecurityContextHolder.getContext().setAuthentication(authentication);

          // token将要过期签发新token, 防止突然退出登录
//          long expiration = (claims.getExpiration().getTime() - new Date().getTime()) / 1000 / 60;
//          if (expiration < configProperties.getTokenRefreshTime()) {
//            String token = JwtUtil.buildToken(jwtSubject, configProperties.getTokenExpireTime(),
//              configProperties.getTokenKey());
//            response.addHeader(Constants.TOKEN_HEADER_NAME, token);
//            loginRecordService.saveAsync(user.getUsername(), LoginRecord.TYPE_REFRESH, null,
//              user.getTenantId(), request);
//          }

        }
      } catch (ExpiredJwtException e) {
        CommonUtil.responseError(response, Constants.TOKEN_EXPIRED_CODE, Constants.TOKEN_EXPIRED_MSG,
          e.getMessage());
        return;
      } catch (Exception e) {
        CommonUtil.responseError(response, Constants.BAD_CREDENTIALS_CODE, Constants.BAD_CREDENTIALS_MSG,
          e.toString());
        return;
      }
    }
    chain.doFilter(request, response);
  }

}
