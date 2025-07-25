package com.gxwebsoft.cms.entity;

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
 * 产品
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsProduct对象", description = "产品")
public class CmsProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    @ApiModelProperty(value = "类型 0软件产品 1实物商品 2虚拟商品")
    private Integer type;

    @ApiModelProperty(value = "产品编码")
    private String code;

    @ApiModelProperty(value = "产品标题")
    private String title;

    @ApiModelProperty(value = "封面图")
    private String image;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "产品详情")
    private String content;

    @ApiModelProperty(value = "父级分类ID")
    private Integer parentId;

    @ApiModelProperty(value = "产品分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "产品规格 0单规格 1多规格")
    private Integer specs;

    @ApiModelProperty(value = "货架")
    private String position;

    @ApiModelProperty(value = "单位名称 (个)")
    private String unitName;

    @ApiModelProperty(value = "进货价格")
    private BigDecimal price;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "库存计算方式(10下单减库存 20付款减库存)")
    private Integer deductStockType;

    @ApiModelProperty(value = "轮播图")
    private String files;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "消费赚取积分")
    private BigDecimal gainIntegral;

    @ApiModelProperty(value = "推荐")
    private Integer recommend;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "状态（0：未上架，1：上架）")
    private Boolean isShow;

    @ApiModelProperty(value = "状态, 0上架 1待上架 2待审核 3审核不通过")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
