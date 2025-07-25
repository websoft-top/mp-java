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
 * 商户
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopMerchant对象", description = "商户")
public class ShopMerchant implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "merchant_id", type = IdType.AUTO)
    private Long merchantId;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "商户编号")
    private String merchantCode;

    @ApiModelProperty(value = "商户类型")
    private Integer type;

    @ApiModelProperty(value = "商户图标")
    private String image;

    @ApiModelProperty(value = "商户手机号")
    private String phone;

    @ApiModelProperty(value = "商户姓名")
    private String realName;

    @ApiModelProperty(value = "店铺类型")
    private String shopType;

    @ApiModelProperty(value = "项目分类")
    private String itemType;

    @ApiModelProperty(value = "商户分类")
    private String category;

    @ApiModelProperty(value = "商户经营分类")
    private Integer merchantCategoryId;

    @ApiModelProperty(value = "商户分类")
    private String merchantCategoryTitle;

    @ApiModelProperty(value = "经纬度")
    private String lngAndLat;

    private String lng;

    private String lat;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "手续费")
    private BigDecimal commission;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "资质图片")
    private String files;

    @ApiModelProperty(value = "营业时间")
    private String businessTime;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "每小时价格")
    private BigDecimal price;

    @ApiModelProperty(value = "是否自营")
    private Integer ownStore;

    @ApiModelProperty(value = "是否可以快递")
    private Boolean canExpress;

    @ApiModelProperty(value = "是否推荐")
    private Integer recommend;

    @ApiModelProperty(value = "是否营业")
    private Integer isOn;

    private String startTime;

    private String endTime;

    @ApiModelProperty(value = "是否需要审核")
    private Integer goodsReview;

    @ApiModelProperty(value = "管理入口")
    private String adminUrl;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "所有人")
    private Integer userId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
