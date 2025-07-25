package com.gxwebsoft.common.core.utils;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.system.entity.KVEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 签名检查和获取签名
 * https://blog.csdn.net/u011628753/article/details/110251445
 * @author leng
 *
 */
public class SignCheckUtil {
  // 签名字段
  public final static String SIGN = "sign";

  /**
   * 签名检查,签名参数中,sign是用于校验的加密值,其他参数按照字母顺序排序,加密,并将其内容链接起来
   *
   * @param params
   * @param key
   * @return
   */
  public static boolean signCheck(JSONObject params, String key) {
    if (null != params) {
      Map<String, String> map = new HashMap<>();

      params.forEach((k, v) -> {
        map.put(k, v.toString());
      });
      return signCheck(map, key);
    }

    return false;
  }

  /**
   * 签名检查,签名参数中,sign是用于校验的加密值,其他参数按照字母顺序排序,加密,并将其内容链接起来
   *
   * @param params
   * @param key
   *            签名key不允许为空
   * @return
   */
  public static boolean signCheck(Map<String, String> params, String key) {
    String sign = params.get(SIGN);// 签名
    if (null == sign) {
      return false;
    }
    String signTemp = getSignString(params,key);
    if (null == signTemp) {
      return false;
    }
    return signTemp.equals(sign);
  }

  /**
   * 获取签名的字符串
   *
   * @param params
   * @param key
   * @return
   */
  public static String getSignString(JSONObject params, String key) {
    if (null != params) {
      Map<String, String> map = new HashMap<>();

      params.forEach((k, v) -> {
        map.put(k, v.toString());
      });
      return getSignString(map, key);
    }

    return null;
  }

  /**
   * 获取签名的字符串
   *
   * @param params
   * @param key
   * @return
   */
  public static String getSignString(Map<String, String> params, String key) {
    // 签名
    if (null == params || params.size() == 0) {
      return null;
    }
    key = (null == key) ? "" : key;
    List<KVEntity<String, String>> list = new ArrayList<>(params.size() - 1);

    params.forEach((k, v) -> {
      if (!SIGN.equals(k)) {
        list.add(KVEntity.build(k, v));
      }
    });

    Collections.sort(list, (obj1, obj2) -> {
      return obj1.getK().compareTo(obj2.getK());
    });

    StringBuffer sb = new StringBuffer();
    for (KVEntity<String, String> kv : list) {
      String value = kv.getV();
      if (!StringUtils.isEmpty(value)) {
        sb.append(kv.getV()).append("-");
      }
    }
    sb.append(key);
    System.out.println("md5加密前的字符串 = " + sb + key);
    String signTemp = SecureUtil.md5(sb.toString()).toLowerCase();
    return signTemp;
  }

  /**
   * 获取微信签名的字符串
   *
   * 注意签名（sign）的生成方式，具体见官方文档（传参都要参与生成签名，且参数名按照字典序排序，最后接上APP_KEY,转化成大写）
   *
   * @param params
   * @param key
   * @return
   */
  public static String getWXSignString(Map<String, String> params, String key) {
    // 签名
    if (null == params || params.size() == 0 || StringUtils.isEmpty(key)) {
      return null;
    }

    List<KVEntity<String, String>> list = new ArrayList<>(params.size() - 1);

    params.forEach((k, v) -> {
      if (!SIGN.equals(k)) {
        list.add(KVEntity.build(k, v));
      }
    });

    Collections.sort(list, (obj1, obj2) -> {
      return obj1.getK().compareTo(obj2.getK());
    });

    StringBuffer sb = new StringBuffer();
    for (KVEntity<String, String> kv : list) {
      String value = kv.getV();
      if (!StringUtils.isEmpty(value)) {
        sb.append(kv.getK() + "=" + value + "&");
      }
    }

    sb.append("key=" + key);
    String signTemp = SecureUtil.md5(sb.toString()).toLowerCase();
    return signTemp;
  }

  /**
   * 微信签名验证
   * @param params
   * @param key
   * @return
   */
  public static boolean WXsignCheck(Map<String, String> params, String key) {
    String sign = params.get(SIGN);
    if (StringUtils.isEmpty(sign)) {
      return false;
    }
    return sign.equals(getWXSignString(params, key));
  }


  /**
   * 白名单校验
   * @param domainName abc.com
   * @return true
   */
  public boolean checkWhiteDomains(List<String> whiteDomains, String domainName) {
    if(whiteDomains == null){
      return true;
    }
    if (whiteDomains.isEmpty()) {
      return true;
    }
    // 服务器域名白名单列表
    whiteDomains.add("server.gxwebsoft.com");
    System.out.println("whiteDomains = " + whiteDomains);
    System.out.println(">>>  domainName = " + domainName);
    for(String item: whiteDomains){
      if(Objects.equals(item, domainName)){
        return true;
      }
    }
    return false;
  }

}
