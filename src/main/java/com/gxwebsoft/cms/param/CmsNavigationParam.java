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
 * 网站导航记录表查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsNavigationParam对象", description = "网站导航记录表查询参数")
public class CmsNavigationParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer navigationId;

    @ApiModelProperty(value = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "模型")
    private String model;

    @ApiModelProperty(value = "标识")
    private String code;

    @ApiModelProperty(value = "菜单路由地址")
    private String path;

    @ApiModelProperty(value = "菜单组件地址, 目录可为空")
    private String component;

    @ApiModelProperty(value = "打开位置")
    private String target;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "图标颜色")
    private String color;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    @QueryField(type = QueryType.EQ)
    private Integer hide;

    @ApiModelProperty(value = "可见类型 0所有人 1登录可见 2密码可见")
    @QueryField(type = QueryType.EQ)
    private Integer permission;

    @ApiModelProperty(value = "访问密码")
    @QueryField(type = QueryType.EQ)
    private String password;

    @ApiModelProperty(value = "访问密码")
    @QueryField(type = QueryType.EQ)
    private String password2;

    @ApiModelProperty(value = "位置 0不限 1顶部 2底部")
    @QueryField(type = QueryType.EQ)
    private Integer position;

    @ApiModelProperty(value = "仅在顶部显示")
    @QueryField(type = QueryType.EQ)
    private Integer top;

    @ApiModelProperty(value = "仅在底部显示")
    @QueryField(type = QueryType.EQ)
    private Integer bottom;

    @ApiModelProperty(value = "菜单侧栏选中的path")
    private String active;

    @ApiModelProperty(value = "其它路由元信息")
    private String meta;

    @ApiModelProperty(value = "css样式")
    private String style;

    @ApiModelProperty(value = "父级栏目路由")
    private String parentPath;

    @ApiModelProperty(value = "父级栏目名称")
    private String parentName;

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "类型（已废弃）")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "绑定的页面（已废弃）")
    @QueryField(type = QueryType.EQ)
    private Integer pageId;

    @ApiModelProperty(value = "是否微信小程序菜单")
    @QueryField(type = QueryType.EQ)
    private Boolean isMpWeixin;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "设为首页")
    @QueryField(type = QueryType.EQ)
    private Integer home;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Boolean recommend;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否开启布局")
    @QueryField(type = QueryType.EQ)
    private Boolean showLayout;

    @ApiModelProperty(value = "是否查询单页内容")
    @QueryField(type = QueryType.EQ)
    private Boolean queryContent;

    @ApiModelProperty(value = "网站创建者用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteUserId;

}
