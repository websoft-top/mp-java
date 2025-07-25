package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品点赞和收藏表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoodsRelation对象", description = "商品点赞和收藏表")
public class ShopGoodsRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "类型(收藏(collect）、点赞(like))")
    private String type;

    @ApiModelProperty(value = "某种类型的商品(普通商品、秒杀商品)")
    private String category;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
