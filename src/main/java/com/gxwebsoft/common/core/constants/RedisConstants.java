package com.gxwebsoft.common.core.constants;

public class RedisConstants {
  // 短信验证码Key
  public static final String SMS_CODE_KEY = "sms";
  // 验证码过期时间
  public static final Long SMS_CODE_TTL = 5L;
  // 微信凭证access-token
  public static final String ACCESS_TOKEN_KEY = "access-token";
  // 空值防止击穿数据库
  public static final Long CACHE_NULL_TTL = 2L;
  // 商户信息
  public static final String MERCHANT_KEY = "merchant";
  // 添加商户定位点
  public static final String MERCHANT_GEO_KEY = "merchant-geo";

  // token
  public static final String TOKEN_USER_ID = "cache:token:";
  // 排行榜
  public static final String USER_RANKING_BY_APPS = "userRankingByApps";
  // 搜索历史
  public static final String SEARCH_HISTORY = "searchHistory";
  // 租户系统设置信息
  public static final String TEN_ANT_SETTING_KEY = "setting";
  // 排行榜Key
  public static final String USER_RANKING_BY_APPS_5 = "cache5:userRankingByApps";



  // 哗啦啦key
  public static final String getAllShop = "allShop";
  public static final String getBaseInfo = "baseInfo";
  public static final String getFoodClassCategory = "foodCategory";
  public static final String getOpenFood = "openFood";
  public static final String haulalaGeoKey = "cache10:hualala-geo";
  public static final String HLL_CART_KEY = "hll-cart";   // hll-cart[shopId]:[userId]
  public static final String HLL_CART_FOOD_KEY = "hll-cart-list";   // hll-cart-list[shopId]:[userId]

}
