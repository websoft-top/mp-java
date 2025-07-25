package com.gxwebsoft.cms.param;

import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 表单设计表查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsFormParam对象", description = "表单设计表查询参数")
public class CmsFormParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
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
    @QueryField(type = QueryType.EQ)
    private Integer submitNumber;

    @ApiModelProperty(value = "页面布局")
    private String layout;

    @ApiModelProperty(value = "是否隐藏顶部图片")
    @QueryField(type = QueryType.EQ)
    private Integer hidePhoto;

    @ApiModelProperty(value = "是否隐藏背景图片")
    @QueryField(type = QueryType.EQ)
    private Integer hideBackground;

    @ApiModelProperty(value = "是否隐藏视频")
    @QueryField(type = QueryType.EQ)
    private Integer hideVideo;

    @ApiModelProperty(value = "背景图片透明度")
    @QueryField(type = QueryType.EQ)
    private BigDecimal opacity;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "商户ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
