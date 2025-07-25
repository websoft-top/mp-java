package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户入驻申请
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopMerchantApply对象", description = "商户入驻申请")
public class ShopMerchantApply implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "ID")
  @TableId(value = "apply_id", type = IdType.AUTO)
  private Integer applyId;

  @ApiModelProperty(value = "类型")
  private Integer type;

  @ApiModelProperty(value = "店铺类型")
  private String shopType;

  @ApiModelProperty(value = "商户名称")
  private String merchantName;

  @ApiModelProperty(value = "商户图标")
  private String image;

  @ApiModelProperty(value = "商户手机号")
  private String phone;

  @ApiModelProperty(value = "商户姓名")
  private String realName;

  @ApiModelProperty(value = "社会信用代码")
  private String merchantCode;

  @ApiModelProperty(value = "身份证号码")
  private String idCard;

  @ApiModelProperty(value = "身份证正面")
  private String sfz1;

  @ApiModelProperty(value = "身份证反面")
  private String sfz2;

  @ApiModelProperty(value = "营业执照")
  private String yyzz;

  @ApiModelProperty(value = "行业父级分类")
  private Integer parentId;

  @ApiModelProperty(value = "行业分类ID")
  private Integer categoryId;

  @ApiModelProperty(value = "行业分类")
  private String category;

  @ApiModelProperty(value = "手续费")
  private BigDecimal commission;

  @ApiModelProperty(value = "关键字")
  private String keywords;

  @ApiModelProperty(value = "资质图片")
  private String files;

  @ApiModelProperty(value = "所有人")
  private Integer userId;

  @ApiModelProperty(value = "是否自营")
  private Integer ownStore;

  @ApiModelProperty(value = "是否推荐")
  private Integer recommend;

  @ApiModelProperty(value = "是否需要审核")
  private Integer goodsReview;

  @ApiModelProperty(value = "工作负责人")
  private String name2;

  @ApiModelProperty(value = "驳回原因")
  private String reason;

  @ApiModelProperty(value = "审核完成时间")
  private Date completedTime;

  @ApiModelProperty(value = "审核状态")
  private Boolean checkStatus;

  @ApiModelProperty(value = "备注")
  private String comments;

  @ApiModelProperty(value = "状态")
  private Integer status;

  @ApiModelProperty(value = "排序号")
  private Integer sortNumber;

  @ApiModelProperty(value = "租户id")
  private Integer tenantId;

  @ApiModelProperty(value = "应用名称")
  @TableField(exist = false)
  private String tenantName;

  @ApiModelProperty(value = "应用图标")
  @TableField(exist = false)
  private String logo;

  @ApiModelProperty(value = "创建时间")
  private Date createTime;

}
