package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小程序端菜单
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsMpMenu对象", description = "小程序端菜单")
public class CmsMpMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "上级id, 0是顶级")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "类型 0功能图标 1订单状态图标 2首页导航图标 3 商城导航图标 4管理人员功能图标")
    private Integer type;

    @ApiModelProperty(value = "是否微信小程序菜单")
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
    private Integer hide;

    @ApiModelProperty(value = "位置 0不限 1顶部 2底部")
    private Integer position;

    @ApiModelProperty(value = "0 第一行 1第二行")
    private Integer rows;

    @ApiModelProperty(value = "菜单侧栏选中的path")
    private String active;

    @ApiModelProperty(value = "其它路由元信息")
    private String meta;

    @ApiModelProperty(value = "绑定的页面")
    private Integer pageId;

    @ApiModelProperty(value = "绑定的文章分类ID")
    private Integer articleCategoryId;

    @ApiModelProperty(value = "绑定的文章ID")
    private Integer articleId;

    @ApiModelProperty(value = "绑定的表单ID")
    private Integer formId;

    @ApiModelProperty(value = "绑定的知识库标识")
    private String bookCode;

    @ApiModelProperty(value = "绑定的商品分类ID")
    private Integer goodsCategoryId;

    @ApiModelProperty(value = "绑定的商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "是否管理人员可见")
    private Integer adminShow;

    @ApiModelProperty(value = "设为首页")
    private Integer home;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
