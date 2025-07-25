package com.gxwebsoft.common.core.utils;

import com.wechat.pay.java.core.Config;

import java.util.HashMap;
import java.util.Map;


public class WxNativeUtil {

  private static final Map<Integer, Config> tenantConfigs = new HashMap<>();

  public static void addConfig(Integer tenantId, Config config) {
    tenantConfigs.put(tenantId, config);
  }

  public static Config getConfig(Integer tenantId) {
    return tenantConfigs.get(tenantId);
  }
}
