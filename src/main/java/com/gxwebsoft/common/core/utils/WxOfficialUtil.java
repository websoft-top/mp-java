package com.gxwebsoft.common.core.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.system.service.SettingService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信公众号工具类
 * @author 科技小王子
 *
 */
@Component
public class WxOfficialUtil {
  private final StringRedisTemplate stringRedisTemplate;
  private Integer tenantId;
  public String appId;
  public String appSecret;
  public String openid;
  public String unionid;
  public String access_token;
  public String expires_in;
  public String nickname;


  @Resource
  private SettingService settingService;
  @Resource
  private ConfigProperties pathConfig;
  @Resource
  private CacheClient cacheClient;

  public WxOfficialUtil(StringRedisTemplate stringRedisTemplate){
    this.stringRedisTemplate = stringRedisTemplate;
  }

  // 实例化客户端
  public WxOfficialUtil client(Integer tenantId) {
    if(tenantId > 0){
      throw new BusinessException(tenantId + "123123");
    }
    this.tenantId = tenantId;
    this.config();
    System.out.println("this.tenantId = " + this.tenantId);
    return this;
  }

  // 开发者ID和秘钥
  private void config() {
    String key = "cache"+ this.tenantId +":setting:wx-official";
    String wxOfficial = stringRedisTemplate.opsForValue().get(key);
    JSONObject data = JSONObject.parseObject(wxOfficial);
    if(data != null){
      this.appId = data.getString("appId");
      this.appSecret = data.getString("appSecret");
    }
    System.out.println("this.appId = " + this.appId);
    System.out.println("this.appSecret = " + this.appSecret);
  }

  // 获取appId
  public String getAppSecret(){
    return this.appSecret;
  }

  public String getCodeUrl() throws UnsupportedEncodingException {
    String encodedReturnUrl = URLEncoder.encode("https://server.gxwebsoft.com/api/open/wx-official/accessToken","UTF-8");
    return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ this.appId +"&redirect_uri=" + encodedReturnUrl + "&response_type=code&scope=snsapi_userinfo&state="+ this.tenantId +"#wechat_redirect";
  }

  // 获取access_token
  public String getAccessToken(String code) {
    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ this.appId +"&secret="+ this.appSecret +"&code="+ code +"&grant_type=authorization_code";
    System.out.println("url = " + url);
    String response = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
    final JSONObject jsonObject = JSONObject.parseObject(response);
    access_token = jsonObject.getString("access_token");
    if(access_token == null){
      throw new BusinessException("获取access_token失败");
    }
    this.openid = jsonObject.getString("openid");
    this.unionid = jsonObject.getString("unionid");
    this.expires_in = jsonObject.getString("expires_in");
    return access_token;
  }

  // 获取userinfo
  public JSONObject getUserInfo(String access_token) {
    String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+ access_token +"&openid="+ this.openid +"&lang=zh_CN";
    System.out.println("url2 = " + url);
    String response = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
    System.out.println("response = " + response);
    if(response == null){
      throw new BusinessException("获取userinfo失败");
    }
    return JSONObject.parseObject(response);
  }
}
