package com.gxwebsoft.cms.entity;

import java.math.BigDecimal;
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
 * 表单设计表
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsForm对象", description = "表单设计表")
public class CmsForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "form_id", type = IdType.AUTO)
    private Integer formId;

    @ApiModelProperty(value = "表单标题")
    private String name;

    @ApiModelProperty(value = "顶部图片")
    private String photo;

    @ApiModelProperty(value = "背景图片")
    private String background;

    @ApiModelProperty(value = "视频文件")
    private String video;

    @ApiModelProperty(value = "提交次数")
    private Integer submitNumber;

    @ApiModelProperty(value = "页面布局")
    private String layout;

    @ApiModelProperty(value = "是否隐藏顶部图片")
    private Integer hidePhoto;

    @ApiModelProperty(value = "是否隐藏背景图片")
    private Integer hideBackground;

    @ApiModelProperty(value = "是否隐藏视频")
    private Integer hideVideo;

    @ApiModelProperty(value = "背景图片透明度")
    private BigDecimal opacity;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "国际化语言")
    private String lang;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

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
