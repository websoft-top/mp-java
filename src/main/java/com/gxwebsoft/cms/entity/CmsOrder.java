package com.gxwebsoft.cms.entity;

import java.math.BigDecimal;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单
 *
 * @author 科技小王子
 * @since 2024-11-25 12:14:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsOrder对象", description = "订单")
public class CmsOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "订单标题")
    private String title;

    @ApiModelProperty(value = "模型名称")
    private String model;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单类型，0商城 1询价 2留言")
    private Integer type;

    @ApiModelProperty(value = "关联文章ID")
    private Integer articleId;

    @ApiModelProperty(value = "关联网站ID")
    private Integer websiteId;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "订单内容")
    private String content;

    @ApiModelProperty(value = "订单附件")
    private String files;

    @ApiModelProperty(value = "订单总额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "实际付款")
    private BigDecimal payPrice;

    @ApiModelProperty(value = "报价询价")
    private BigDecimal price;

    @ApiModelProperty(value = "购买数量")
    private Integer totalNum;

    @ApiModelProperty(value = "二维码地址，保存订单号，支付成功后才生成")
    private String qrcode;

    @ApiModelProperty(value = "下单渠道，0网站 1小程序 2其他")
    private Integer channel;

    @ApiModelProperty(value = "过期时间")
    private Date expirationTime;

    @ApiModelProperty(value = "订单是否已结算(0未结算 1已结算)")
    private Boolean isSettled;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "国际化语言")
    private String lang;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "图像验证码")
    @TableField(exist = false)
    private String code;

    @ApiModelProperty(value = "栏目ID")
    @TableField(exist = false)
    private Integer categoryId;

    public String getLang() {
      if(this.lang == null || this.lang.equals("zh")){
        return "zh_CN";
      }
      return this.lang;
    }
}
