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
 * 发货单商品查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopOrderDeliveryGoodsParam对象", description = "发货单商品查询参数")
public class ShopOrderDeliveryGoodsParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "发货单ID")
    @QueryField(type = QueryType.EQ)
    private Integer deliveryId;

    @ApiModelProperty(value = "订单ID")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "订单商品ID")
    @QueryField(type = QueryType.EQ)
    private Integer orderGoodsId;

    @ApiModelProperty(value = "商品ID")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "发货数量")
    @QueryField(type = QueryType.EQ)
    private Integer deliveryNum;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
