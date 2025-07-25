package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 小程序信息
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsMp对象", description = "小程序信息")
public class CmsMp implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "mp_id", type = IdType.AUTO)
    private Integer mpId;

    @ApiModelProperty(value = "是否主账号")
    private Integer type;

    @ApiModelProperty(value = "小程序ID")
    private String appId;

    @ApiModelProperty(value = "小程序密钥")
    private String appSecret;

    @ApiModelProperty(value = "小程序名称")
    private String mpName;

    @ApiModelProperty(value = "小程序简称")
    private String shortName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "小程序码")
    private String mpQrcode;

    @ApiModelProperty(value = "微信认证")
    private Integer authentication;

    @ApiModelProperty(value = "主体信息")
    private String companyName;

    @ApiModelProperty(value = "小程序备案")
    private String icpNo;

    @ApiModelProperty(value = "登录邮箱")
    private String email;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "原始ID")
    private String ghId;

    @ApiModelProperty(value = "入口页面")
    private String mainPath;

    @ApiModelProperty(value = "过期时间")
    private Date expirationTime;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "介绍")
    private String comments;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
