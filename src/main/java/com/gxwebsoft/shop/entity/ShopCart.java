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
 * 购物车
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopCart对象", description = "购物车")
public class ShopCart implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车表ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类型 0商城 1外卖")
    private Integer type;

    @ApiModelProperty(value = "唯一标识")
    private String code;

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(value = "商品规格")
    private String spec;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品数量")
    private Integer cartNum;

    @ApiModelProperty(value = "单商品合计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "0 = 未购买 1 = 已购买")
    private Boolean isPay;

    @ApiModelProperty(value = "是否为立即购买")
    private Boolean isNew;

    @ApiModelProperty(value = "是否为立即购买")
    private Boolean isShow;

    @ApiModelProperty(value = "拼团id")
    private Integer combinationId;

    @ApiModelProperty(value = "秒杀产品ID")
    private Integer seckillId;

    @ApiModelProperty(value = "砍价id")
    private Integer bargainId;

    @ApiModelProperty(value = "是否选中")
    private Boolean selected;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
