package com.gxwebsoft.cms.param;

import java.math.BigDecimal;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网站模版查询参数
 *
 * @author 科技小王子
 * @since 2025-01-21 14:21:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsTemplateParam对象", description = "网站模版查询参数")
public class CmsTemplateParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "模版名称")
    private String name;

    @ApiModelProperty(value = "模版标识")
    private String code;

    @ApiModelProperty(value = "缩列图")
    private String image;

    @ApiModelProperty(value = "类型 1企业官网 2其他")
    @QueryField(type = QueryType.EQ)
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
    @QueryField(type = QueryType.EQ)
    private Integer version;

    @ApiModelProperty(value = "行业类型(父级)")
    private String industryParent;

    @ApiModelProperty(value = "行业类型(子级)")
    private String industryChild;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Boolean recommend;

    @ApiModelProperty(value = "是否共享模板")
    @QueryField(type = QueryType.EQ)
    private Boolean share;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
