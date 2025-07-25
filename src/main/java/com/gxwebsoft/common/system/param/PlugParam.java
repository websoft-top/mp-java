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
 * 插件扩展查询参数
 *
 * @author 科技小王子
 * @since 2023-05-18 11:57:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "PlugParam对象", description = "插件扩展查询参数")
public class PlugParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "插件id")
    @QueryField(type = QueryType.EQ)
    private Integer plugId;

    @ApiModelProperty(value = "菜单id")
    @QueryField(type = QueryType.EQ)
    private Integer menuId;

    @ApiModelProperty(value = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "菜单路由地址")
    private String path;

    @ApiModelProperty(value = "菜单组件地址, 目录可为空")
    private String component;

    @ApiModelProperty(value = "类型, 0菜单, 1按钮")
    @QueryField(type = QueryType.EQ)
    private Integer menuType;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "权限标识")
    private String authority;

    @ApiModelProperty(value = "打开位置")
    private String target;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "图标颜色")
    private String color;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    @QueryField(type = QueryType.EQ)
    private Integer hide;

    @ApiModelProperty(value = "菜单侧栏选中的path")
    private String active;

    @ApiModelProperty(value = "其它路由元信息")
    private String meta;

    @ApiModelProperty(value = "关联应用ID")
    @QueryField(type = QueryType.EQ)
    private Integer appId;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "商户编码")
    private String merchantCode;

}
