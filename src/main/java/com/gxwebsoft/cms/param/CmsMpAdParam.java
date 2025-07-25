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
 * 小程序广告位查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsMpAdParam对象", description = "小程序广告位查询参数")
public class CmsMpAdParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer adId;

    @ApiModelProperty(value = "页面ID")
    @QueryField(type = QueryType.EQ)
    private Integer pageId;

    @ApiModelProperty(value = "广告类型")
    private String adType;

    @ApiModelProperty(value = "广告位名称")
    private String name;

    @ApiModelProperty(value = "宽")
    private String width;

    @ApiModelProperty(value = "高")
    private String height;

    @ApiModelProperty(value = "广告图片")
    private String images;

    @ApiModelProperty(value = "路由/链接地址")
    private String path;

    @ApiModelProperty(value = "页面名称")
    private String pageName;

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

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
