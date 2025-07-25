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
 * 商品日志表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopGoodsLogParam对象", description = "商品日志表查询参数")
public class ShopGoodsLogParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "统计ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "类型visit,cart,order,pay,collect,refund")
    private String type;

    @ApiModelProperty(value = "商品ID")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "是否浏览")
    @QueryField(type = QueryType.EQ)
    private Boolean visitNum;

    @ApiModelProperty(value = "加入购物车数量")
    @QueryField(type = QueryType.EQ)
    private Integer cartNum;

    @ApiModelProperty(value = "下单数量")
    @QueryField(type = QueryType.EQ)
    private Integer orderNum;

    @ApiModelProperty(value = "支付数量")
    @QueryField(type = QueryType.EQ)
    private Integer payNum;

    @ApiModelProperty(value = "支付金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal payPrice;

    @ApiModelProperty(value = "商品成本价")
    @QueryField(type = QueryType.EQ)
    private BigDecimal costPrice;

    @ApiModelProperty(value = "支付用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer payUid;

    @ApiModelProperty(value = "退款数量")
    @QueryField(type = QueryType.EQ)
    private Integer refundNum;

    @ApiModelProperty(value = "退款金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal refundPrice;

    @ApiModelProperty(value = "收藏")
    @QueryField(type = QueryType.EQ)
    private Boolean collectNum;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

}
