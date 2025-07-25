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
 * 商品点赞和收藏表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopGoodsRelationParam对象", description = "商品点赞和收藏表查询参数")
public class ShopGoodsRelationParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "商品ID")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "类型(收藏(collect）、点赞(like))")
    private String type;

    @ApiModelProperty(value = "某种类型的商品(普通商品、秒杀商品)")
    private String category;

}
