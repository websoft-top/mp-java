package com.gxwebsoft.shop.param;

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
 * 商品分类查询参数
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopCategoryParam对象", description = "商品分类查询参数")
public class ShopCategoryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "上级id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "模型")
    private String model;

    @ApiModelProperty(value = "标识")
    private String code;

    @ApiModelProperty(value = "链接地址")
    private String path;

    @ApiModelProperty(value = "组件地址")
    private String component;

    @ApiModelProperty(value = "打开位置")
    private String target;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "banner")
    private String banner;

    @ApiModelProperty(value = "图标颜色")
    private String color;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    @QueryField(type = QueryType.EQ)
    private Integer hide;

    @ApiModelProperty(value = "可见类型 0所有人 1登录可见 2密码可见")
    @QueryField(type = QueryType.EQ)
    private Integer permission;

    @ApiModelProperty(value = "访问密码")
    private String password;

    @ApiModelProperty(value = "位置 0不限 1顶部 2底部")
    @QueryField(type = QueryType.EQ)
    private Integer position;

    @ApiModelProperty(value = "仅在顶部显示")
    @QueryField(type = QueryType.EQ)
    private Integer top;

    @ApiModelProperty(value = "仅在底部显示")
    @QueryField(type = QueryType.EQ)
    private Integer bottom;

    @ApiModelProperty(value = "菜单选中的path")
    private String active;

    @ApiModelProperty(value = "其它路由元信息")
    private String meta;

    @ApiModelProperty(value = "css样式")
    private String style;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "商户ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "语言")
    private String lang;

    @ApiModelProperty(value = "设为首页")
    @QueryField(type = QueryType.EQ)
    private Integer home;

    @ApiModelProperty(value = "推荐")
    @QueryField(type = QueryType.EQ)
    private Integer recommend;

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

}
