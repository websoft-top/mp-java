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
 * 分销商订单记录表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopDealerOrderParam对象", description = "分销商订单记录表查询参数")
public class ShopDealerOrderParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "买家用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "订单ID")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "订单总金额(不含运费)")
    @QueryField(type = QueryType.EQ)
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "分销商用户id(一级)")
    @QueryField(type = QueryType.EQ)
    private Integer firstUserId;

    @ApiModelProperty(value = "分销商用户id(二级)")
    @QueryField(type = QueryType.EQ)
    private Integer secondUserId;

    @ApiModelProperty(value = "分销商用户id(三级)")
    @QueryField(type = QueryType.EQ)
    private Integer thirdUserId;

    @ApiModelProperty(value = "分销佣金(一级)")
    @QueryField(type = QueryType.EQ)
    private BigDecimal firstMoney;

    @ApiModelProperty(value = "分销佣金(二级)")
    @QueryField(type = QueryType.EQ)
    private BigDecimal secondMoney;

    @ApiModelProperty(value = "分销佣金(三级)")
    @QueryField(type = QueryType.EQ)
    private BigDecimal thirdMoney;

    @ApiModelProperty(value = "订单是否失效(0未失效 1已失效)")
    @QueryField(type = QueryType.EQ)
    private Integer isInvalid;

    @ApiModelProperty(value = "佣金结算(0未结算 1已结算)")
    @QueryField(type = QueryType.EQ)
    private Integer isSettled;

    @ApiModelProperty(value = "结算时间")
    @QueryField(type = QueryType.EQ)
    private Integer settleTime;

}
