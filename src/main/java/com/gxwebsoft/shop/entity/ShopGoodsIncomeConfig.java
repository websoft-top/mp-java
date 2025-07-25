package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分润配置
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoodsIncomeConfig对象", description = "分润配置")
public class ShopGoodsIncomeConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goodsId;

    @ApiModelProperty(value = "店铺类型")
    private String merchantShopType;

    private Integer skuId;

    @ApiModelProperty(value = "比例")
    private BigDecimal rate;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
