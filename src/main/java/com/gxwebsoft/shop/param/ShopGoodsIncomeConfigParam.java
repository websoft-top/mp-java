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
 * 分润配置查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopGoodsIncomeConfigParam对象", description = "分润配置查询参数")
public class ShopGoodsIncomeConfigParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "店铺类型")
    private String merchantShopType;

    @QueryField(type = QueryType.EQ)
    private Integer skuId;

    @ApiModelProperty(value = "比例")
    @QueryField(type = QueryType.EQ)
    private BigDecimal rate;

    @ApiModelProperty(value = "用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
