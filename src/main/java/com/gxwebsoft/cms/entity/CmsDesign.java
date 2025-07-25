package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 页面管理记录表
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsDesign对象", description = "页面管理记录表")
public class CmsDesign implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "page_id", type = IdType.AUTO)
    private Integer pageId;

    @ApiModelProperty(value = "页面标题")
    private String name;

    @ApiModelProperty(value = "所属栏目ID")
    private Integer categoryId;

    @ApiModelProperty(value = "页面模型")
    private String model;

    @ApiModelProperty(value = "页面关键词")
    private String keywords;

    @ApiModelProperty(value = "页面描述")
    private String description;

    @ApiModelProperty(value = "路由地址")
    @TableField(exist = false)
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField(exist = false)
    private String component;

    @ApiModelProperty(value = "缩列图")
    private String photo;

    @ApiModelProperty(value = "购买链接")
    private String buyUrl;

    @ApiModelProperty(value = "页面样式")
    private String style;

    @ApiModelProperty(value = "页面内容")
    private String content;

    @ApiModelProperty(value = "是否开启布局")
    private Boolean showLayout;

    @ApiModelProperty(value = "页面布局")
    @TableField(exist = false)
    private String layout;

    @ApiModelProperty(value = "是否显示banner")
    private Boolean showBanner;

    @ApiModelProperty(value = "是否显示Button")
    private Boolean showButton;

    @ApiModelProperty(value = "上级id, 0是顶级")
    private Integer parentId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "国际化语言")
    private String lang;

    @ApiModelProperty(value = "用于同步翻译内容")
    @TableField(exist = false)
    private Integer langCategoryId;

    @ApiModelProperty(value = "是否启用自动翻译")
    private Boolean translation;

    @ApiModelProperty(value = "设为首页")
    private Integer home;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

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
