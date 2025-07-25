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
 * 网站域名记录表
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsDomain对象", description = "网站域名记录表")
public class CmsDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型 0赠送域名 1绑定域名 ")
    private Integer type;

    @ApiModelProperty(value = "域名")
    private String domain;

    @ApiModelProperty(value = "主机记录")
    private String hostName;

    @ApiModelProperty(value = "记录值")
    private String hostValue;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "网站ID")
    private Integer websiteId;

    @ApiModelProperty(value = "租户ID")
    private Integer appId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
