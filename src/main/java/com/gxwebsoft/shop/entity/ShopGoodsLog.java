package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品日志表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoodsLog对象", description = "商品日志表")
public class ShopGoodsLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "统计ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型visit,cart,order,pay,collect,refund")
    private String type;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "是否浏览")
    private Boolean visitNum;

    @ApiModelProperty(value = "加入购物车数量")
    private Integer cartNum;

    @ApiModelProperty(value = "下单数量")
    private Integer orderNum;

    @ApiModelProperty(value = "支付数量")
    private Integer payNum;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payPrice;

    @ApiModelProperty(value = "商品成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "支付用户ID")
    private Integer payUid;

    @ApiModelProperty(value = "退款数量")
    private Integer refundNum;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundPrice;

    @ApiModelProperty(value = "收藏")
    private Boolean collectNum;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
