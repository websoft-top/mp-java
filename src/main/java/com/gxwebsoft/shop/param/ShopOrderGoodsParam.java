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
 * 商品信息查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopOrderGoodsParam对象", description = "商品信息查询参数")
public class ShopOrderGoodsParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "关联订单表id")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "订单标识")
    private String orderCode;

    @ApiModelProperty(value = "关联商户ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "商品封面图")
    private String image;

    @ApiModelProperty(value = "关联商品id")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品规格")
    private String spec;

    @QueryField(type = QueryType.EQ)
    private Integer skuId;

    @ApiModelProperty(value = "单价")
    @QueryField(type = QueryType.EQ)
    private BigDecimal price;

    @ApiModelProperty(value = "购买数量")
    @QueryField(type = QueryType.EQ)
    private Integer totalNum;

    @ApiModelProperty(value = "0 未付款 1已付款，2无需付款或占用状态")
    @QueryField(type = QueryType.EQ)
    private Integer payStatus;

    @ApiModelProperty(value = "0未使用，1已完成，2已取消，3取消中，4退款申请中，5退款被拒绝，6退款成功，7客户端申请退款")
    @QueryField(type = QueryType.EQ)
    private Integer orderStatus;

    @ApiModelProperty(value = "是否免费：0收费、1免费")
    @QueryField(type = QueryType.EQ)
    private Boolean isFree;

    @ApiModelProperty(value = "系统版本 0当前版本 其他版本")
    @QueryField(type = QueryType.EQ)
    private Integer version;

    @ApiModelProperty(value = "预约时间段")
    private String timePeriod;

    @ApiModelProperty(value = "预定日期")
    private String dateTime;

    @ApiModelProperty(value = "开场时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "毫秒时间戳")
    private Long timeFlag;

    @ApiModelProperty(value = "过期时间")
    private String expirationTime;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

}
