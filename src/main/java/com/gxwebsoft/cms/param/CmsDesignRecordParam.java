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
 * 页面组件表查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsDesignRecordParam对象", description = "页面组件表查询参数")
public class CmsDesignRecordParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "关联导航ID")
    @QueryField(type = QueryType.EQ)
    private Integer navigationId;

    @ApiModelProperty(value = "组件")
    private String title;

    @ApiModelProperty(value = "组件标识")
    private String dictCode;

    @ApiModelProperty(value = "组件样式")
    private String styles;

    @ApiModelProperty(value = "卡片阴影显示时机")
    private String shadow;

    @ApiModelProperty(value = "页面关键词")
    private String keywords;

    @ApiModelProperty(value = "页面描述")
    private String description;

    @ApiModelProperty(value = "页面路由地址")
    private String path;

    @ApiModelProperty(value = "缩列图")
    private String photo;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

}
