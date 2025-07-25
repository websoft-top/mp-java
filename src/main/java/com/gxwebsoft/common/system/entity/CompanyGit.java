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
 * 代码仓库
 *
 * @author 科技小王子
 * @since 2024-10-19 18:08:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CompanyGit对象", description = "代码仓库")
@TableName("sys_company_git")
public class CompanyGit implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "仓库名称")
    private String title;

    @ApiModelProperty(value = "厂商")
    private String brand;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "企业ID")
    private Integer companyId;

    @ApiModelProperty(value = "仓库地址")
    private String domain;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "仓库描述")
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
