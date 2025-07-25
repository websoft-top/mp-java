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
 * 网站设置
 *
 * @author 科技小王子
 * @since 2025-02-19 01:35:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsWebsiteSetting对象", description = "网站设置")
public class CmsWebsiteSetting implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联网站ID")
    private Integer websiteId;

    @ApiModelProperty(value = "是否官方插件")
    private Boolean official;

    @ApiModelProperty(value = "是否展示在插件市场")
    private Boolean market;

    @ApiModelProperty(value = "是否允许被搜索")
    private Boolean search;

    @ApiModelProperty(value = "是否共享")
    private Boolean share;

    @ApiModelProperty(value = "文章是否需要审核")
    private Boolean articleReview;

    @ApiModelProperty(value = "是否插件 0应用1 插件 ")
    private Boolean plugin;

    @ApiModelProperty(value = "编辑器类型 1 富文本编辑器 2 Markdown编辑器")
    private Integer editor;

    @ApiModelProperty(value = "显示站内搜索")
    private Boolean searchBtn;

    @ApiModelProperty(value = "显示登录注册功能")
    private Boolean loginBtn;

    @ApiModelProperty(value = "显示悬浮客服工具")
    private Boolean floatTool;

    @ApiModelProperty(value = "显示版权链接")
    private Boolean copyrightLink;

    @ApiModelProperty(value = "导航栏最多显示数量")
    private Boolean maxMenuNum;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

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
