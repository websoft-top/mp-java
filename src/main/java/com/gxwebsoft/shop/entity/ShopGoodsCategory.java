package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类
 *
 * @author 科技小王子
 * @since 2025-05-01 00:36:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoodsCategory对象", description = "商品分类")
public class ShopGoodsCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品分类ID")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty(value = "分类标识")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String title;

    @ApiModelProperty(value = "类型 0商城分类 1外卖分类")
    private Integer type;

    @ApiModelProperty(value = "分类图片")
    private String image;

    @ApiModelProperty(value = "上级分类ID")
    private Integer parentId;

    @ApiModelProperty(value = "路由/链接地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "绑定的页面")
    private Integer pageId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "商品数量")
    private Integer count;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    private Integer hide;

    @ApiModelProperty(value = "是否推荐")
    private Integer recommend;

    @ApiModelProperty(value = "是否显示在首页")
    private Integer showIndex;

    @ApiModelProperty(value = "商铺ID")
    private Long merchantId;

    @ApiModelProperty(value = "状态, 0正常, 1禁用")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

}
