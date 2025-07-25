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
 * 商品多规格查询参数
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopGoodsSpecParam对象", description = "商品多规格查询参数")
public class ShopGoodsSpecParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "规格ID")
    @QueryField(type = QueryType.EQ)
    private Integer specId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格值")
    private String specValue;

    @ApiModelProperty(value = "活动类型 0=商品，1=秒杀，2=砍价，3=拼团")
    @QueryField(type = QueryType.EQ)
    private Boolean type;

}
