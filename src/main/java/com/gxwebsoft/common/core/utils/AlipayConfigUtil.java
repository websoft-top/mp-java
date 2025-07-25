package com.gxwebsoft.common.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.exception.BusinessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 支付宝工具类
 * @author leng
 *
 */
@Component
public class AlipayConfigUtil {
  private final StringRedisTemplate stringRedisTemplate;
  public Integer tenantId;
  public String gateway;
  public JSONObject config;
  public String appId;
  public String privateKey;
  public String appCertPublicKey;
  public String alipayCertPublicKey;
  public String alipayRootCert;

  @Resource
  private ConfigProperties pathConfig;

  public AlipayConfigUtil(StringRedisTemplate stringRedisTemplate){
    this.stringRedisTemplate = stringRedisTemplate;
  }

  // 实例化客户端
  public DefaultAlipayClient alipayClient(Integer tenantId) throws AlipayApiException {
    this.gateway = "https://openapi.alipay.com/gateway.do";
    this.tenantId = tenantId;
    this.payment(tenantId);
    CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
    certAlipayRequest.setServerUrl(this.gateway);
    certAlipayRequest.setAppId(this.appId);
    certAlipayRequest.setPrivateKey(this.privateKey);
    certAlipayRequest.setFormat(AlipayConstants.FORMAT_JSON);
    certAlipayRequest.setCharset(AlipayConstants.CHARSET_UTF8);
    certAlipayRequest.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
    certAlipayRequest.setCertPath(this.appCertPublicKey);
    certAlipayRequest.setAlipayPublicCertPath(this.alipayCertPublicKey);
    certAlipayRequest.setRootCertPath(this.alipayRootCert);
//    System.out.println("this.appId = " + this.appId);
//    System.out.println("this.appId = " + this.gateway);
//    System.out.println("this.appId = " + this.privateKey);
//    System.out.println("this.appId = " + this.appCertPublicKey);
//    System.out.println("this.appId = " + this.alipayCertPublicKey);
//    System.out.println("this.appId = " + this.alipayRootCert);
//    System.out.println("this.config = " + this.config);
    return new DefaultAlipayClient(certAlipayRequest);
  }

  /**
   * 获取支付宝秘钥
   */
  public JSONObject payment(Integer tenantId) {
    System.out.println("tenantId = " + tenantId);
    String key = "cache".concat(tenantId.toString()).concat(":setting:payment");
    System.out.println("key = " + key);
    String cache = stringRedisTemplate.opsForValue().get(key);
    if (cache == null) {
      throw new BusinessException("支付方式未配置");
    }
    // 解析json数据
    JSONObject payment = JSON.parseObject(cache.getBytes());
    this.config = payment;
    this.appId = payment.getString("alipayAppId");
    this.privateKey = payment.getString("privateKey");
    this.appCertPublicKey = pathConfig.getUploadPath() + "file" + payment.getString("appCertPublicKey");
    this.alipayCertPublicKey = pathConfig.getUploadPath() + "file" + payment.getString("alipayCertPublicKey");
    this.alipayRootCert = pathConfig.getUploadPath() + "file" + payment.getString("alipayRootCert");
    return payment;
  }

  public String appId(){
    return this.appId;
  }

  public String privateKey(){
    return this.privateKey;
  }

  public String appCertPublicKey(){
    return this.appCertPublicKey;
  }

  public String alipayCertPublicKey(){
    return this.alipayCertPublicKey;
  }

  public String alipayRootCert(){
    return this.alipayRootCert;
  }




}
