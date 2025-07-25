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
 * 发货单查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopOrderDeliveryParam对象", description = "发货单查询参数")
public class ShopOrderDeliveryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "发货单ID")
    @QueryField(type = QueryType.EQ)
    private Integer deliveryId;

    @ApiModelProperty(value = "订单ID")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "发货方式(10手动录入 20无需物流 30电子面单)")
    @QueryField(type = QueryType.EQ)
    private Integer deliveryMethod;

    @ApiModelProperty(value = "打包方式(废弃)")
    @QueryField(type = QueryType.EQ)
    private Integer packMethod;

    @ApiModelProperty(value = "物流公司ID")
    @QueryField(type = QueryType.EQ)
    private Integer expressId;

    @ApiModelProperty(value = "物流单号")
    private String expressNo;

    @ApiModelProperty(value = "电子面单模板内容")
    private String eorderHtml;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
