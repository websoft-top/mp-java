package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员充值订单表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopRechargeOrder对象", description = "会员充值订单表")
public class ShopRechargeOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "充值方式(10自定义金额 20套餐充值)")
    private Integer rechargeType;

    @ApiModelProperty(value = "机构id")
    private Integer organizationId;

    @ApiModelProperty(value = "充值套餐ID")
    private Integer planId;

    @ApiModelProperty(value = "用户支付金额")
    private BigDecimal payPrice;

    @ApiModelProperty(value = "赠送金额")
    private BigDecimal giftMoney;

    @ApiModelProperty(value = "实际到账金额")
    private BigDecimal actualMoney;

    @ApiModelProperty(value = "用户可用余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "支付方式（微信/支付宝）")
    private String payMethod;

    @ApiModelProperty(value = "支付状态(10待支付 20已支付)")
    private Integer payStatus;

    @ApiModelProperty(value = "付款时间")
    private Integer payTime;

    @ApiModelProperty(value = "第三方交易记录ID")
    private Integer tradeId;

    @ApiModelProperty(value = "来源客户端 (APP、H5、小程序等)")
    private String platform;

    @ApiModelProperty(value = "所属门店ID")
    private Integer shopId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "商户编码")
    private String merchantCode;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
