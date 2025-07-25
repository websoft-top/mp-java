package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网站模版
 *
 * @author 科技小王子
 * @since 2025-01-21 14:21:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsTemplate对象", description = "网站模版")
public class CmsTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "模版名称")
    private String name;

    @ApiModelProperty(value = "模版标识")
    private String code;

    @ApiModelProperty(value = "缩列图")
    private String image;

    @ApiModelProperty(value = "类型 1企业官网 2其他")
    private Integer type;

    @ApiModelProperty(value = "网站关键词")
    private String keywords;

    @ApiModelProperty(value = "域名前缀")
    private String prefix;

    @ApiModelProperty(value = "预览地址")
    private String domain;

    @ApiModelProperty(value = "模版下载地址")
    private String downUrl;

    @ApiModelProperty(value = "色系")
    private String color;

    @ApiModelProperty(value = "应用版本 10免费版 20授权版 30永久授权")
    private Integer version;

    @ApiModelProperty(value = "行业类型(父级)")
    private String industryParent;

    @ApiModelProperty(value = "行业类型(子级)")
    private String industryChild;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "是否共享模板")
    private Boolean share;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

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
