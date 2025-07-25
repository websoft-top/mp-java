package com.gxwebsoft.common.core.constants;

public class OrderConstants {
  // 支付方式
  public static final String PAY_METHOD_BALANCE = "10"; // 余额支付
  public static final String PAY_METHOD_WX = "20";      // 微信支付
  public static final String PAY_METHOD_ALIPAY = "30";  // 支付宝支付
  public static final String PAY_METHOD_OTHER = "40";   // 其他支付

  // 付款状态
  public static final Integer PAY_STATUS_NO_PAY = 10; // 未付款
  public static final Integer PAY_STATUS_SUCCESS = 20; // 已付款

  // 发货状态
  public static final Integer DELIVERY_STATUS_NO = 10; // 未发货
  public static final Integer DELIVERY_STATUS_YES = 20; // 已发货
  public static final Integer DELIVERY_STATUS_30 = 30; // 部分发货

  // 收货状态
  public static final Integer RECEIPT_STATUS_NO = 10; // 未收货
  public static final Integer RECEIPT_STATUS_YES = 20; // 已收货
  public static final Integer RECEIPT_STATUS_RETURN = 30; // 已退货

  // 订单状态
  public static final Integer ORDER_STATUS_DOING = 10; // 进行中
  public static final Integer ORDER_STATUS_CANCEL = 20; // 已取消
  public static final Integer ORDER_STATUS_TO_CANCEL = 21; // 待取消
  public static final Integer ORDER_STATUS_COMPLETED = 30; // 已完成

  // 订单结算状态
  public static final Integer ORDER_SETTLED_YES = 1; // 已结算
  public static final Integer ORDER_SETTLED_NO = 0; // 未结算




}
