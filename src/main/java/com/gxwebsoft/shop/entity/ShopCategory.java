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
 * @since 2025-04-24 20:52:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopCategory对象", description = "商品分类")
public class ShopCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "上级id, 0是顶级")
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
    private Integer hide;

    @ApiModelProperty(value = "可见类型 0所有人 1登录可见 2密码可见")
    private Integer permission;

    @ApiModelProperty(value = "访问密码")
    private String password;

    @ApiModelProperty(value = "位置 0不限 1顶部 2底部")
    private Integer position;

    @ApiModelProperty(value = "仅在顶部显示")
    private Integer top;

    @ApiModelProperty(value = "仅在底部显示")
    private Integer bottom;

    @ApiModelProperty(value = "菜单选中的path")
    private String active;

    @ApiModelProperty(value = "其它路由元信息")
    private String meta;

    @ApiModelProperty(value = "css样式")
    private String style;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "语言")
    private String lang;

    @ApiModelProperty(value = "设为首页")
    private Integer home;

    @ApiModelProperty(value = "推荐")
    private Integer recommend;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
