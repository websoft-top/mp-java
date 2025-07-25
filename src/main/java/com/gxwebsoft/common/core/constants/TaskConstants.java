package com.gxwebsoft.common.core.constants;

public class TaskConstants {
  // 工单进度
  public static final Integer TOBEARRANGED = 0; // 待安排
  public static final Integer PENDING = 1; // 待处理
  public static final Integer PROCESSING = 2; // 处理中
  public static final Integer TOBECONFIRMED = 3; // 待评价
  public static final Integer COMPLETED = 4; // 已完成
  public static final Integer CLOSED = 5; // 已关闭

  // 工单状态
  public static final Integer TASK_STATUS_0 = 0; // 待处理
  public static final Integer TASK_STATUS_1 = 1; // 已完成

  // 操作类型
  public static final String ACTION_1 = "派单";
  public static final String ACTION_2 = "已解决";
  public static final String ACTION_3 = "关单";
  public static final String ACTION_4 = "分享";
  public static final String ACTION_5 = "编辑";
}
