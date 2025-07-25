package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组件
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsComponents对象", description = "组件")
public class CmsComponents implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "组件标题")
    private String title;

    @ApiModelProperty(value = "关联导航ID")
    private Integer navigationId;

    @ApiModelProperty(value = "组件类型")
    private String type;

    @ApiModelProperty(value = "页面关键词")
    private String keywords;

    @ApiModelProperty(value = "页面描述")
    private String description;

    @ApiModelProperty(value = "组件路径")
    private String path;

    @ApiModelProperty(value = "组件图标")
    private String icon;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
