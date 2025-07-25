package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 插件扩展
 *
 * @author 科技小王子
 * @since 2023-05-18 11:57:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Plug对象", description = "插件扩展")
@TableName("sys_plug")
public class Plug implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int TYPE_MENU = 0;  // 菜单类型
    public static final int TYPE_BTN = 1;  // 按钮类型

    @ApiModelProperty(value = "插件id")
    @TableId(value = "plug_id", type = IdType.AUTO)
    private Integer plugId;

    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;

    @ApiModelProperty(value = "上级id, 0是顶级")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "菜单路由地址")
    private String path;

    @ApiModelProperty(value = "菜单组件地址, 目录可为空")
    private String component;

    @ApiModelProperty(value = "类型, 0菜单, 1按钮")
    private Integer menuType;

    @ApiModelProperty(value = "排序号")
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
    private Integer hide;

    @ApiModelProperty(value = "菜单侧栏选中的path")
    private String active;

    @ApiModelProperty(value = "其它路由元信息")
    private String meta;

    @ApiModelProperty(value = "插件描述")
    private String comments;

    @ApiModelProperty(value = "插件详情")
    private String content;

    @ApiModelProperty("评分")
    private BigDecimal score;

    @ApiModelProperty("插件价格")
    private BigDecimal price;

    @ApiModelProperty("浏览次数")
    private Integer clicks;

    @ApiModelProperty("安装次数")
    private Integer installs;

    @ApiModelProperty(value = "关联应用ID")
    private Integer appId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "商户编码")
    private String merchantCode;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty("子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    @ApiModelProperty("角色权限树选中状态")
    @TableField(exist = false)
    private Boolean checked;

    @ApiModelProperty("租户名称")
    @TableField(exist = false)
    private String tenantName;

    @ApiModelProperty("企业名称")
    @TableField(exist = false)
    private String companyName;

    @ApiModelProperty("企业简称")
    @TableField(exist = false)
    private String shortName;

    @ApiModelProperty("企业域名")
    @TableField(exist = false)
    private String domain;
}
