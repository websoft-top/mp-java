package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用域名
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CompanyUrl对象", description = "应用域名")
@TableName("sys_company_url")
public class CompanyUrl implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "域名类型")
    private String type;

    @ApiModelProperty(value = "企业ID")
    private Integer companyId;

    @ApiModelProperty(value = "域名")
    private String domain;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "二维码")
    private String qrcode;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0正常, 1待确认")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
