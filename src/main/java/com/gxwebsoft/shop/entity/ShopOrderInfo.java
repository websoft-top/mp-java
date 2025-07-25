package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 场地
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopOrderInfo对象", description = "场地")
public class ShopOrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联订单表id")
    private Integer orderId;

    @ApiModelProperty(value = "组合数据:日期+时间段+场馆id+场地id")
    private String orderCode;

    @ApiModelProperty(value = "关联商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "关联场地id")
    private Integer fieldId;

    @ApiModelProperty(value = "场地名称")
    private String fieldName;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "儿童价")
    private BigDecimal childrenPrice;

    @ApiModelProperty(value = "成人人数")
    private Integer adultNum;

    @ApiModelProperty(value = "儿童人数")
    private Integer childrenNum;

    @ApiModelProperty(value = "已核销的成人票数")
    private Integer adultNumUse;

    @ApiModelProperty(value = "已核销的儿童票数")
    private Integer childrenNumUse;

    @ApiModelProperty(value = "0 未付款 1已付款，2无需付款或占用状态")
    private Integer payStatus;

    @ApiModelProperty(value = "0未使用，1已完成，2已取消，3取消中，4退款申请中，5退款被拒绝，6退款成功，7客户端申请退款")
    private Integer orderStatus;

    @ApiModelProperty(value = "是否免费：0收费、1免费")
    private Boolean isFree;

    @ApiModelProperty(value = "是否支持儿童票：0不支持、1支持")
    private Boolean isChildren;

    @ApiModelProperty(value = "系统版本 0当前版本 其他版本")
    private Integer version;

    @ApiModelProperty(value = "预订类型：0全场，1半场")
    private Boolean isHalf;

    @ApiModelProperty(value = "预约时间段")
    private String timePeriod;

    @ApiModelProperty(value = "预定日期")
    private LocalDate dateTime;

    @ApiModelProperty(value = "开场时间")
    private LocalTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalTime endTime;

    @ApiModelProperty(value = "毫秒时间戳")
    private Long timeFlag;

    @ApiModelProperty(value = "过期时间")
    private Date expirationTime;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
