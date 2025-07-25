package com.gxwebsoft.common.system.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户查询参数
 *
 * @author 科技小王子
 * @since 2023-07-17 17:49:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TenantParam对象", description = "租户查询参数")
public class TenantParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租户名称")
    private String tenantName;

    @ApiModelProperty(value = "租户编号")
    private String tenantCode;

    @ApiModelProperty(value = "logo")
    private String logo;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    @QueryField(type = QueryType.EQ)
    private Integer tenantId;

}
