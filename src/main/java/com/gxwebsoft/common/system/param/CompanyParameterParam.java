package com.gxwebsoft.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用参数查询参数
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CompanyParameterParam对象", description = "应用参数查询参数")
public class CompanyParameterParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "参数名称")
    private String name;

    @ApiModelProperty(value = "参数内容")
    private String value;

    @ApiModelProperty(value = "企业ID")
    @QueryField(type = QueryType.EQ)
    private Integer companyId;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0正常, 1待确认")
    @QueryField(type = QueryType.EQ)
    private Integer status;

}
