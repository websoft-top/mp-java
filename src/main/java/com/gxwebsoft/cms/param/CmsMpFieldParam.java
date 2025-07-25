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
 * 小程序配置查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsMpFieldParam对象", description = "小程序配置查询参数")
public class CmsMpFieldParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "类型，0文本 1图片 2其他")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "名称")
    private String value;

    @ApiModelProperty(value = "页面ID")
    @QueryField(type = QueryType.EQ)
    private Integer pageId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
