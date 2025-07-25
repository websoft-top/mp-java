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
 * 网站设置查询参数
 *
 * @author 科技小王子
 * @since 2025-02-19 01:35:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsWebsiteSettingParam对象", description = "网站设置查询参数")
public class CmsWebsiteSettingParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "关联网站ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteId;

    @ApiModelProperty(value = "是否官方插件")
    @QueryField(type = QueryType.EQ)
    private Boolean official;

    @ApiModelProperty(value = "是否展示在插件市场")
    @QueryField(type = QueryType.EQ)
    private Boolean market;

    @ApiModelProperty(value = "是否允许被搜索")
    @QueryField(type = QueryType.EQ)
    private Boolean search;

    @ApiModelProperty(value = "是否共享")
    @QueryField(type = QueryType.EQ)
    private Boolean share;

    @ApiModelProperty(value = "是否插件 0应用1 插件 ")
    @QueryField(type = QueryType.EQ)
    private Boolean plugin;

    @ApiModelProperty(value = "编辑器类型 1 md-editor-v3, 2 tinymce-editor")
    @QueryField(type = QueryType.EQ)
    private Boolean editor;

    @ApiModelProperty(value = "显示站内搜索")
    @QueryField(type = QueryType.EQ)
    private Boolean searchBtn;

    @ApiModelProperty(value = "显示登录注册功能")
    @QueryField(type = QueryType.EQ)
    private Boolean loginBtn;

    @ApiModelProperty(value = "显示悬浮客服工具")
    @QueryField(type = QueryType.EQ)
    private Boolean floatTool;

    @ApiModelProperty(value = "显示版权链接")
    @QueryField(type = QueryType.EQ)
    private Boolean copyrightLink;

    @ApiModelProperty(value = "导航栏最多显示数量")
    @QueryField(type = QueryType.EQ)
    private Boolean maxMenuNum;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
