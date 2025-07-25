package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品多规格
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoodsSpec对象", description = "商品多规格")
public class ShopGoodsSpec implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "规格ID")
    private Integer specId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格值")
    private String specValue;

    @ApiModelProperty(value = "活动类型 0=商品，1=秒杀，2=砍价，3=拼团")
    private Boolean type;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
