package com.gxwebsoft.shop.param;

import java.math.BigDecimal;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员充值订单表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopRechargeOrderParam对象", description = "会员充值订单表查询参数")
public class ShopRechargeOrderParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "充值方式(10自定义金额 20套餐充值)")
    @QueryField(type = QueryType.EQ)
    private Integer rechargeType;

    @ApiModelProperty(value = "机构id")
    @QueryField(type = QueryType.EQ)
    private Integer organizationId;

    @ApiModelProperty(value = "充值套餐ID")
    @QueryField(type = QueryType.EQ)
    private Integer planId;

    @ApiModelProperty(value = "用户支付金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal payPrice;

    @ApiModelProperty(value = "赠送金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal giftMoney;

    @ApiModelProperty(value = "实际到账金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal actualMoney;

    @ApiModelProperty(value = "用户可用余额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal balance;

    @ApiModelProperty(value = "支付方式（微信/支付宝）")
    private String payMethod;

    @ApiModelProperty(value = "支付状态(10待支付 20已支付)")
    @QueryField(type = QueryType.EQ)
    private Integer payStatus;

    @ApiModelProperty(value = "付款时间")
    @QueryField(type = QueryType.EQ)
    private Integer payTime;

    @ApiModelProperty(value = "第三方交易记录ID")
    @QueryField(type = QueryType.EQ)
    private Integer tradeId;

    @ApiModelProperty(value = "来源客户端 (APP、H5、小程序等)")
    private String platform;

    @ApiModelProperty(value = "所属门店ID")
    @QueryField(type = QueryType.EQ)
    private Integer shopId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "商户编码")
    private String merchantCode;

}
