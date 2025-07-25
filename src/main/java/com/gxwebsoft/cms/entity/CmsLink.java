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
 * 常用链接
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsLink对象", description = "常用链接")
public class CmsLink implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "链接名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "栏目ID")
    private Integer categoryId;

    @ApiModelProperty(value = "应用ID")
    private Integer appId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "国际化语言")
    private String lang;

    @ApiModelProperty(value = "是否推荐")
    private Integer recommend;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "状态, 0正常, 1待确认")
    private Integer status;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "栏目名称")
    @TableField(exist = false)
    private String categoryName;

}
