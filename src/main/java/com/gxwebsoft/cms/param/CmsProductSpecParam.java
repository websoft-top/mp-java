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
 * 规格查询参数
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsProductSpecParam对象", description = "规格查询参数")
public class CmsProductSpecParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规格ID")
    @QueryField(type = QueryType.EQ)
    private Integer specId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格值")
    private String specValue;

    @ApiModelProperty(value = "创建用户")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "更新者")
    @QueryField(type = QueryType.EQ)
    private Integer updater;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1待修,2异常已修，3异常未修")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

}
