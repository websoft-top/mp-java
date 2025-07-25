package com.gxwebsoft.cms.param;

import java.math.BigDecimal;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模型查询参数
 *
 * @author 科技小王子
 * @since 2024-11-26 15:44:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsModelParam对象", description = "模型查询参数")
public class CmsModelParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer modelId;

    @ApiModelProperty(value = "模型名称")
    private String name;

    @ApiModelProperty(value = "唯一标识")
    private String model;

    @ApiModelProperty(value = "菜单路由地址")
    private String componentDetail;

    @ApiModelProperty(value = "菜单组件地址, 目录可为空")
    private String component;

    @ApiModelProperty(value = "模型banner图片")
    private String banner;

    @ApiModelProperty(value = "封面图宽")
    @QueryField(type = QueryType.EQ)
    private String imageWidth;

    @ApiModelProperty(value = "封面图高")
    @QueryField(type = QueryType.EQ)
    private String imageHeight;

    @ApiModelProperty(value = "Banner上的标题")
    private String title;

    @ApiModelProperty(value = "列表显示方式(10小图展示 20大图展示)")
    @QueryField(type = QueryType.EQ)
    private Integer showType;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0已发布, 1待审核 2已驳回 3违规内容")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "创建者用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteUserId;

}
