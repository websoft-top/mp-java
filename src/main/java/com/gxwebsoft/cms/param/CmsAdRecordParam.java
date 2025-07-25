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
 * 广告图片查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsAdRecordParam对象", description = "广告图片查询参数")
public class CmsAdRecordParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer adRecordId;

    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "图片地址")
    private String path;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "广告位ID")
    @QueryField(type = QueryType.EQ)
    private Integer adId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

}
