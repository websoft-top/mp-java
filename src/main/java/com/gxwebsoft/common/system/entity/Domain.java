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
import java.time.LocalDateTime;

/**
 * 授权域名
 *
 * @author 科技小王子
 * @since 2024-09-19 23:56:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Domain对象", description = "授权域名")
@TableName("sys_domain")
public class Domain implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "域名")
    private String domain;

    @ApiModelProperty(value = "主机记录")
    private String hostName;

    @ApiModelProperty(value = "记录值")
    private String hostValue;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "类型 0常规 1后台 2商家端")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "企业ID")
    private Integer companyId;

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
