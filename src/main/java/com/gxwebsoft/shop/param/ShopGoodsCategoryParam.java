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
 * @since 2025-05-01 00:36:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopGoodsCategoryParam对象", description = "商品分类查询参数")
public class ShopGoodsCategoryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品分类ID")
    @QueryField(type = QueryType.EQ)
    private Integer categoryId;

    @ApiModelProperty(value = "分类标识")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String title;

    @ApiModelProperty(value = "类型 0商城分类 1外卖分类")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "分类图片")
    private String image;

    @ApiModelProperty(value = "上级分类ID")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "路由/链接地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "绑定的页面")
    @QueryField(type = QueryType.EQ)
    private Integer pageId;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "商品数量")
    @QueryField(type = QueryType.EQ)
    private Integer count;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    @QueryField(type = QueryType.EQ)
    private Integer hide;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Integer recommend;

    @ApiModelProperty(value = "是否显示在首页")
    @QueryField(type = QueryType.EQ)
    private Integer showIndex;

    @ApiModelProperty(value = "商铺ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "状态, 0正常, 1禁用")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
