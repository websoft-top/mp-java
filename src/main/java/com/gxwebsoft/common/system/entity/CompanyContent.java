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
 * 应用详情
 *
 * @author 科技小王子
 * @since 2024-10-16 13:41:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CompanyContent对象", description = "应用详情")
@TableName("sys_company_content")
public class CompanyContent implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业ID")
    private Integer companyId;

    @ApiModelProperty(value = "详细内容")
    private String content;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
