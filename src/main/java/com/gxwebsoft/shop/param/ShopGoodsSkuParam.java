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
 * 商品sku列表查询参数
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopGoodsSkuParam对象", description = "商品sku列表查询参数")
public class ShopGoodsSkuParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "商品属性索引值 (attr_value|attr_value[|....])")
    private String sku;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "商品价格")
    @QueryField(type = QueryType.EQ)
    private BigDecimal price;

    @ApiModelProperty(value = "市场价格")
    @QueryField(type = QueryType.EQ)
    private BigDecimal salePrice;

    @ApiModelProperty(value = "成本价")
    @QueryField(type = QueryType.EQ)
    private BigDecimal cost;

    @ApiModelProperty(value = "库存")
    @QueryField(type = QueryType.EQ)
    private Integer stock;

    @ApiModelProperty(value = "sku编码")
    private String skuNo;

    @ApiModelProperty(value = "商品条码")
    private String barCode;

    @ApiModelProperty(value = "重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal weight;

    @ApiModelProperty(value = "体积")
    @QueryField(type = QueryType.EQ)
    private BigDecimal volume;

    @ApiModelProperty(value = "唯一值")
    private String uuid;

    @ApiModelProperty(value = "状态, 0正常, 1异常")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String comments;

}
