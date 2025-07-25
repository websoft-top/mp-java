package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付方式
 *
 * @author 科技小王子
 * @since 2024-05-11 12:39:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Payment对象", description = "支付方式")
@TableName("sys_payment")
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "支付方式")
    private String name;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "标识")
    private String code;

    @ApiModelProperty(value = "支付图标")
    private String image;

    @ApiModelProperty(value = "微信商户号类型 1普通商户2子商户")
    private Integer wechatType;

    @ApiModelProperty(value = "应用ID")
    private String appId;

    @ApiModelProperty(value = "商户号")
    private String mchId;

    @ApiModelProperty(value = "设置APIv3密钥")
    private String apiKey;

    @ApiModelProperty(value = "证书文件 (CERT)")
    private String apiclientCert;

    @ApiModelProperty(value = "证书文件 (KEY)")
    private String apiclientKey;

    @ApiModelProperty(value = "公钥文件 (KEY)")
    private String pubKey;

    @ApiModelProperty(value = "公钥ID")
    private String pubKeyId;

    @ApiModelProperty(value = "商户证书序列号")
    private String merchantSerialNumber;

    @ApiModelProperty(value = "支付结果通知")
    private String notifyUrl;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "文章排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0未启用, 1启用")
    private Boolean status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
