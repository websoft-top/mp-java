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
 * 小程序端菜单查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsMpMenuParam对象", description = "小程序端菜单查询参数")
public class CmsMpMenuParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer menuId;

    @ApiModelProperty(value = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "类型 0功能图标 1订单状态图标 2首页导航图标 3 商城导航图标 4管理人员功能图标")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "是否微信小程序菜单")
    @QueryField(type = QueryType.EQ)
    private Boolean isMpWeixin;

    @ApiModelProperty(value = "菜单路由地址")
    private String path;

    @ApiModelProperty(value = "菜单组件地址, 目录可为空")
    private String component;

    @ApiModelProperty(value = "打开位置")
    private String target;

    @ApiModelProperty(value = "菜单图标")
    private String avatar;

    @ApiModelProperty(value = "图标颜色")
    private String color;

    @ApiModelProperty(value = "上传图标")
    private String icon;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    @QueryField(type = QueryType.EQ)
    private Integer hide;

    @ApiModelProperty(value = "位置 0不限 1顶部 2底部")
    @QueryField(type = QueryType.EQ)
    private Integer position;

    @ApiModelProperty(value = "0 第一行 1第二行")
    @QueryField(type = QueryType.EQ)
    private Integer rows;

    @ApiModelProperty(value = "菜单侧栏选中的path")
    private String active;

    @ApiModelProperty(value = "其它路由元信息")
    private String meta;

    @ApiModelProperty(value = "绑定的页面")
    @QueryField(type = QueryType.EQ)
    private Integer pageId;

    @ApiModelProperty(value = "绑定的文章分类ID")
    @QueryField(type = QueryType.EQ)
    private Integer articleCategoryId;

    @ApiModelProperty(value = "绑定的文章ID")
    @QueryField(type = QueryType.EQ)
    private Integer articleId;

    @ApiModelProperty(value = "绑定的表单ID")
    @QueryField(type = QueryType.EQ)
    private Integer formId;

    @ApiModelProperty(value = "绑定的知识库标识")
    private String bookCode;

    @ApiModelProperty(value = "绑定的商品分类ID")
    @QueryField(type = QueryType.EQ)
    private Integer goodsCategoryId;

    @ApiModelProperty(value = "绑定的商品ID")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "是否管理人员可见")
    @QueryField(type = QueryType.EQ)
    private Integer adminShow;

    @ApiModelProperty(value = "设为首页")
    @QueryField(type = QueryType.EQ)
    private Integer home;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

}
