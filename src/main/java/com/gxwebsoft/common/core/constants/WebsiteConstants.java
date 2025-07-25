package com.gxwebsoft.common.core.constants;

public class WebsiteConstants extends BaseConstants {
  // 运行状态 0未开通 1运行中 2维护中 3已关闭 4已欠费停机 5违规关停
  public static final String[] WEBSITE_STATUS_NAME = {"未开通","运行中","维护中","已关闭","已欠费停机","违规关停"};
  // 状态图标
  public static final String[] WEBSITE_STATUS_ICON = {"error","success","warning","error","error","error"};
  // 关闭原因
  public static final String[] WEBSITE_STATUS_TEXT = {"产品未开通","","系统升级维护","","已欠费停机","违规关停"};
  // 跳转地址
  public static final String[] WEBSITE_STATUS_URL = {"https://websoft.top","","","","https://websoft.top/user","https://websoft.top/user"};
  // 跳转按钮文字
  public static final String[] WEBSITE_STATUS_BTN_TEXT = {"立即开通","","","","立即续费","申请解封"};
}
