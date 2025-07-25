package com.gxwebsoft.cms.param;

import java.math.BigDecimal;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单查询参数
 *
 * @author 科技小王子
 * @since 2024-11-25 12:14:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsOrderParam对象", description = "订单查询参数")
public class CmsOrderParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    @QueryField(type = QueryType.EQ)
    private Integer orderId;

    @ApiModelProperty(value = "订单标题")
    private String title;

    @ApiModelProperty(value = "模型名称")
    private String model;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单类型，0商城 1询价 2留言")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "关联文章ID")
    @QueryField(type = QueryType.EQ)
    private Integer articleId;

    @ApiModelProperty(value = "关联网站ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteId;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "订单内容")
    private String content;

    @ApiModelProperty(value = "订单总额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "实际付款")
    @QueryField(type = QueryType.EQ)
    private BigDecimal payPrice;

    @ApiModelProperty(value = "报价询价")
    @QueryField(type = QueryType.EQ)
    private BigDecimal price;

    @ApiModelProperty(value = "购买数量")
    @QueryField(type = QueryType.EQ)
    private Integer totalNum;

    @ApiModelProperty(value = "二维码地址，保存订单号，支付成功后才生成")
    private String qrcode;

    @ApiModelProperty(value = "下单渠道，0网站 1小程序 2其他")
    @QueryField(type = QueryType.EQ)
    private Integer channel;

    @ApiModelProperty(value = "过期时间")
    private String expirationTime;

    @ApiModelProperty(value = "订单是否已结算(0未结算 1已结算)")
    @QueryField(type = QueryType.EQ)
    private Boolean isSettled;

    @ApiModelProperty(value = "用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "网站创建者ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteUserId;

}
