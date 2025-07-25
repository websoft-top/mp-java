package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品sku列表
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoodsSku对象", description = "商品sku列表")
public class ShopGoodsSku implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "商品属性索引值 (attr_value|attr_value[|....])")
    private String sku;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "成本价")
    private BigDecimal cost;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "sku编码")
    private String skuNo;

    @ApiModelProperty(value = "商品条码")
    private String barCode;

    @ApiModelProperty(value = "重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "体积")
    private BigDecimal volume;

    @ApiModelProperty(value = "唯一值")
    private String uuid;

    @ApiModelProperty(value = "状态, 0正常, 1异常")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
