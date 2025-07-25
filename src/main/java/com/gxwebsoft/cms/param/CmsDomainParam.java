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
 * 网站域名记录表查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsDomainParam对象", description = "网站域名记录表查询参数")
public class CmsDomainParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "类型 0赠送域名 1绑定域名 ")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "域名")
    private String domain;

    @ApiModelProperty(value = "主机记录")
    private String hostName;

    @ApiModelProperty(value = "记录值")
    private String hostValue;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "网站ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteId;

    @ApiModelProperty(value = "租户ID")
    @QueryField(type = QueryType.EQ)
    private Integer appId;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
