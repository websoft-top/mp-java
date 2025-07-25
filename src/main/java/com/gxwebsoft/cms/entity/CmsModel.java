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
 * 模型
 *
 * @author 科技小王子
 * @since 2024-11-26 15:44:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsModel对象", description = "模型")
public class CmsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "model_id", type = IdType.AUTO)
    private Integer modelId;

    @ApiModelProperty(value = "模型名称")
    private String name;

    @ApiModelProperty(value = "唯一标识")
    private String model;

    @ApiModelProperty(value = "列表页路径")
    private String component;

    @ApiModelProperty(value = "详情页路径")
    private String componentDetail;

    @ApiModelProperty(value = "模型banner图片")
    private String banner;

    @ApiModelProperty(value = "文章后缀")
    private String suffix;

    @ApiModelProperty(value = "拇指图片")
    private String thumb;

    @ApiModelProperty(value = "封面图宽")
    private String imageWidth;

    @ApiModelProperty(value = "封面图高")
    private String imageHeight;

    @ApiModelProperty(value = "css样式")
    private String style;

    @ApiModelProperty(value = "Banner上的标题")
    private String title;

    @ApiModelProperty(value = "列表显示方式(10小图展示 20大图展示)")
    private Integer showType;

    @ApiModelProperty(value = "是否禁用")
    private Boolean disabled;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0已发布, 1待审核 2已驳回 3违规内容")
    private Integer status;

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
