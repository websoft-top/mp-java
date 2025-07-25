package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoods对象", description = "商品")
public class ShopGoods implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "产品编码")
    private String code;

    @ApiModelProperty(value = "类型 0软件产品 1实物商品 2虚拟商品")
    private Integer type;

    @ApiModelProperty(value = "封面图")
    private String image;

    @ApiModelProperty(value = "父级分类ID")
    private Integer parentId;

    @ApiModelProperty(value = "产品分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "产品规格 0单规格 1多规格")
    private Integer specs;

    @ApiModelProperty(value = "货架")
    private String position;

    @ApiModelProperty(value = "单位名称 (个)")
    private String unitName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "进货价格")
    private BigDecimal buyingPrice;

    @ApiModelProperty(value = "经销商价格")
    private BigDecimal dealerPrice;

    @ApiModelProperty(value = "库存计算方式(10下单减库存 20付款减库存)")
    private Integer deductStockType;

    @ApiModelProperty(value = "交付方式(0不启用)")
    private Integer deliveryMethod;

    @ApiModelProperty(value = "购买时长(0不启用，1 一次性，2 按时长)")
    private Integer durationMethod;

    @ApiModelProperty(value = "可购买数量")
    private Integer canBuyNumber;

    @ApiModelProperty(value = "商品详情")
    private String content;

    @ApiModelProperty(value = "轮播图")
    private String files;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "安装次数")
    private Integer install;

    @ApiModelProperty(value = "评分")
    private BigDecimal rate;

    @ApiModelProperty(value = "消费赚取积分")
    private BigDecimal gainIntegral;

    @ApiModelProperty(value = "推荐")
    private Integer recommend;

    @ApiModelProperty(value = "是否官方")
    private Integer official;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "是否展示")
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
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
