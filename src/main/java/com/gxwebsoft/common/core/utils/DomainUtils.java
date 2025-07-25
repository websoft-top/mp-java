package com.gxwebsoft.common.core.utils;

import com.gxwebsoft.cms.entity.CmsDomain;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DomainUtils {
  public static boolean isDomainResolvable(String domain) {
    try {
      InetAddress.getByName(domain);
      return true;
    } catch (UnknownHostException e) {
      return false;
    }
  }

  public static boolean DNSLookup(CmsDomain domain){
    try {
      // 获取域名对应的InetAddress对象
      InetAddress inetAddress = InetAddress.getByName(domain.getDomain());
      final String hostAddress = inetAddress.getHostAddress();
      InetAddress inetAddress2 = InetAddress.getByName(domain.getHostValue());
      final String hostAddress2 = inetAddress2.getHostAddress();
      if(hostAddress.equals(hostAddress2)){
        return true;
      }
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
