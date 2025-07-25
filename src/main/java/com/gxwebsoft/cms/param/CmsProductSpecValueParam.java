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
 * 规格值查询参数
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsProductSpecValueParam对象", description = "规格值查询参数")
public class CmsProductSpecValueParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规格值ID")
    @QueryField(type = QueryType.EQ)
    private Integer specValueId;

    @ApiModelProperty(value = "规格组ID")
    @QueryField(type = QueryType.EQ)
    private Integer specId;

    @ApiModelProperty(value = "规格值")
    private String specValue;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

}
