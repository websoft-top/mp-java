package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 押金
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopWechatDeposit对象", description = "押金")
public class ShopWechatDeposit implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单id")
    private Integer oid;

    @ApiModelProperty(value = "用户id")
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
    private BigDecimal price;

    @ApiModelProperty(value = "押金状态，1已付款，2未付款，已退押金")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
