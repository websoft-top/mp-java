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
 * 订单核销查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopOrderInfoLogParam对象", description = "订单核销查询参数")
public class ShopOrderInfoLogParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "关联订单表id")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "关联商户ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "关联场地id")
    @QueryField(type = QueryType.EQ)
    private Integer fieldId;

    @ApiModelProperty(value = "核销数量")
    @QueryField(type = QueryType.EQ)
    private Boolean useNum;

}
