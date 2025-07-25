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
 * 押金查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopWechatDepositParam对象", description = "押金查询参数")
public class ShopWechatDepositParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "订单id")
    @QueryField(type = QueryType.EQ)
    private Integer oid;

    @ApiModelProperty(value = "用户id")
    @QueryField(type = QueryType.EQ)
    private Integer uid;

    @ApiModelProperty(value = "场地订单号")
    private String orderNum;

    @ApiModelProperty(value = "付款订单号")
    private String wechatOrder;

    @ApiModelProperty(value = "退款订单号 ")
    private String wechatReturn;

    @ApiModelProperty(value = "场馆名称")
    private String siteName;

    @ApiModelProperty(value = "微信昵称")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "物品名称")
    private String name;

    @ApiModelProperty(value = "押金金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal price;

    @ApiModelProperty(value = "押金状态，1已付款，2未付款，已退押金")
    @QueryField(type = QueryType.EQ)
    private Boolean status;

}
