package com.gxwebsoft.common.core.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.core.exception.BusinessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 企业微信工具类
 * @author 科技小王子
 *
 */
@Component
public class WxWorkUtil {
  private final StringRedisTemplate stringRedisTemplate;
  private Integer tenantId;
  public String appId;
  public String appSecret;
  public String access_token;
  public String expires_in;
  public String nickname;
  public String userid;
  public String user_ticket;
  public String openid;
  public String external_userid;
  public String name;
  public String position;
  public String mobile;
  public String gender;
  public String email;
  public String avatar;
  public String thumb_avatar;
  public String telephone;
  public String address;
  public String alias;
  public String qr_code;
  public String open_userid;

  @Resource
  private CacheClient cacheClient;


  public WxWorkUtil(StringRedisTemplate stringRedisTemplate){
    this.stringRedisTemplate = stringRedisTemplate;
  }


  // 实例化客户端
  public WxWorkUtil client(Integer tenantId) {
    this.tenantId = tenantId;
    this.config();
    return this;
  }

  // 开发者ID和秘钥
  private void config() {
    JSONObject settingInfo = cacheClient.getSettingInfo("wx-work", this.tenantId);
    if(settingInfo == null){
      throw new BusinessException("企业微信未配置");
    }
    this.appId = settingInfo.getString("corpId");
    this.appSecret = settingInfo.getString("secret");
    System.out.println("this.appId = " + this.appId);
    System.out.println("this.appSecret = " + this.appSecret);
  }

  // 获取access_token
  public void getAccessToken(String code) {
    String key = "cache"+ this.tenantId +":ww:access_token";
    final String access_token = stringRedisTemplate.opsForValue().get(key);
    if(access_token != null){
      this.getUserInfo(code,access_token);
    }else {
      String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" +this.appId+ "&corpsecret="+ this.appSecret;
      System.out.println("url = " + url);
      String response = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
      System.out.println("response = " + response);
      final JSONObject jsonObject = JSONObject.parseObject(response);
      // 获取成功
      if(jsonObject.getString("access_token") != null){
        this.access_token = jsonObject.getString("access_token");
        this.expires_in = jsonObject.getString("expires_in");
        stringRedisTemplate.opsForValue().set(key,this.access_token,7000, TimeUnit.SECONDS);
        System.out.println("获取access_token成功 = " + this.access_token);
        this.getUserInfo(code,this.access_token);
      }
    }
  }

  // 获取userinfo
  public void getUserInfo(String code, String access_token) {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/auth/getuserinfo?access_token=" +access_token+ "&code=" + code;
    System.out.println("url2 = " + url);
    String response = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
    System.out.println("response = " + response);
    JSONObject jsonObject = JSONObject.parseObject(response);
    final String errcode = jsonObject.getString("errcode");
    final String errmsg = jsonObject.getString("errmsg");
    if(!StrUtil.equals(errcode,"0")){
      throw new BusinessException(errmsg);
    }
    this.userid = jsonObject.getString("userid");
    this.user_ticket = jsonObject.getString("user_ticket");
    this.openid = jsonObject.getString("openid");
    this.external_userid = jsonObject.getString("external_userid");
    System.out.println("获取用户信息成功 = " + jsonObject);
  }

  public void getUserProfile(String userid, String access_token) {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+ access_token +"&userid=" + userid;
    String response = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
    System.out.println("response3 = " + response);
    JSONObject jsonObject = JSONObject.parseObject(response);
    System.out.println("读取用户详细信息 = " + jsonObject);

    this.name = jsonObject.getString("name");
    this.position = jsonObject.getString("position");
    this.gender = jsonObject.getString("gender");
    this.email = jsonObject.getString("email");
    this.avatar = jsonObject.getString("avatar");
    this.thumb_avatar = jsonObject.getString("thumb_avatar");
    this.telephone = jsonObject.getString("telephone");
    this.address = jsonObject.getString("address");
    this.alias = jsonObject.getString("alias");
    this.qr_code = jsonObject.getString("qr_code");
    this.open_userid = jsonObject.getString("open_userid");
  }
}
