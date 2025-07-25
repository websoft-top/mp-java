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
 * 应用详情查询参数
 *
 * @author 科技小王子
 * @since 2024-10-16 13:41:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CompanyContentParam对象", description = "应用详情查询参数")
public class CompanyContentParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "企业ID")
    @QueryField(type = QueryType.EQ)
    private Integer companyId;

    @ApiModelProperty(value = "详细内容")
    private String content;

}
