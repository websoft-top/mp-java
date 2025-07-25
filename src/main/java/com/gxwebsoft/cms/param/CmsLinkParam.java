package com.gxwebsoft.cms.param;

import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 常用链接查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsLinkParam对象", description = "常用链接查询参数")
public class CmsLinkParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @QueryField(type = QueryType.EQ)
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
    @QueryField(type = QueryType.EQ)
    private Integer appId;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Integer recommend;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "状态, 0正常, 1待确认")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "网站创建者ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteUserId;

}
