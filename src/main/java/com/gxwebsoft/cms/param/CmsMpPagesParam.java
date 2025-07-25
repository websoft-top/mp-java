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
 * 小程序页面查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsMpPagesParam对象", description = "小程序页面查询参数")
public class CmsMpPagesParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "页面名称")
    private String title;

    @ApiModelProperty(value = "页面路径")
    private String path;

    @ApiModelProperty(value = "设为首页")
    @QueryField(type = QueryType.EQ)
    private Integer home;

    @ApiModelProperty(value = "分包")
    private String subpackage;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "未选中图标")
    private String iconPath;

    @ApiModelProperty(value = "选中的图标")
    private String selectedIconPath;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
