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
 * 分销商资金明细表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopDealerCapitalParam对象", description = "分销商资金明细表查询参数")
public class ShopDealerCapitalParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "分销商用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "订单ID")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "资金流动类型 (10佣金收入 20提现支出 30转账支出 40转账收入)")
    @QueryField(type = QueryType.EQ)
    private Integer flowType;

    @ApiModelProperty(value = "金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal money;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "对方用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer toUserId;

}
