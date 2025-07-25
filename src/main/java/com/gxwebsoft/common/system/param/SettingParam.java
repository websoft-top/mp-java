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
 * 系统设置查询参数
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "SettingParam对象", description = "系统设置查询参数")
public class SettingParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @QueryField(type = QueryType.EQ)
    private Integer settingId;

    @ApiModelProperty(value = "设置项标示")
    @QueryField(type = QueryType.EQ)
    private String settingKey;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "同步更新租户名称")
    private String tenantName;

    @ApiModelProperty(value = "租户名称")
    private Integer tenantId;

}
