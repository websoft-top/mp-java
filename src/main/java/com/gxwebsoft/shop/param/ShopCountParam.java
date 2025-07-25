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
 * 商城销售统计表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopCountParam对象", description = "商城销售统计表查询参数")
public class ShopCountParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "统计日期")
    private String dateTime;

    @ApiModelProperty(value = "总销售额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "今日销售额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal todayPrice;

    @ApiModelProperty(value = "总会员数")
    @QueryField(type = QueryType.EQ)
    private BigDecimal totalUsers;

    @ApiModelProperty(value = "今日新增")
    @QueryField(type = QueryType.EQ)
    private BigDecimal todayUsers;

    @ApiModelProperty(value = "总订单笔数")
    @QueryField(type = QueryType.EQ)
    private BigDecimal totalOrders;

    @ApiModelProperty(value = "今日订单笔数")
    @QueryField(type = QueryType.EQ)
    private BigDecimal todayOrders;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

}
