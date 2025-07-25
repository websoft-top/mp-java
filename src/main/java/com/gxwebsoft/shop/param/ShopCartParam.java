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
 * 购物车查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopCartParam对象", description = "购物车查询参数")
public class ShopCartParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车表ID")
    @QueryField(type = QueryType.EQ)
    private Long id;

    @ApiModelProperty(value = "类型 0商城 1外卖")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "唯一标识")
    private String code;

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(value = "商品规格")
    private String spec;

    @ApiModelProperty(value = "商品价格")
    @QueryField(type = QueryType.EQ)
    private BigDecimal price;

    @ApiModelProperty(value = "商品数量")
    @QueryField(type = QueryType.EQ)
    private Integer cartNum;

    @ApiModelProperty(value = "单商品合计")
    @QueryField(type = QueryType.EQ)
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "0 = 未购买 1 = 已购买")
    @QueryField(type = QueryType.EQ)
    private Boolean isPay;

    @ApiModelProperty(value = "是否为立即购买")
    @QueryField(type = QueryType.EQ)
    private Boolean isNew;

    @ApiModelProperty(value = "是否为立即购买")
    @QueryField(type = QueryType.EQ)
    private Boolean isShow;

    @ApiModelProperty(value = "拼团id")
    @QueryField(type = QueryType.EQ)
    private Integer combinationId;

    @ApiModelProperty(value = "秒杀产品ID")
    @QueryField(type = QueryType.EQ)
    private Integer seckillId;

    @ApiModelProperty(value = "砍价id")
    @QueryField(type = QueryType.EQ)
    private Integer bargainId;

    @ApiModelProperty(value = "是否选中")
    @QueryField(type = QueryType.EQ)
    private Boolean selected;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

}
