package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网站导航记录表
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsNavigation对象", description = "网站导航记录表")
public class CmsNavigation implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "navigation_id", type = IdType.AUTO)
    private Integer navigationId;

    @ApiModelProperty(value = "类型, 0列表 1图文")
    private Integer type;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "上级id, 0是顶级")
    private Integer parentId;

    @ApiModelProperty(value = "模型")
    private String model;

    @ApiModelProperty(value = "标识")
    private String code;

    @ApiModelProperty(value = "菜单路由地址")
    private String path;

    @ApiModelProperty(value = "菜单组件地址, 目录可为空")
    private String component;

    @ApiModelProperty(value = "文件后缀")
    @TableField(exist = false)
    private String suffix;

    @ApiModelProperty(value = "打开位置")
    private String target;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "banner")
    @TableField(exist = false)
    private String banner;

    @ApiModelProperty(value = "移动端banner")
    @TableField(exist = false)
    private String mpBanner;

    @ApiModelProperty(value = "图标颜色")
    private String color;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    private Integer hide;

    @ApiModelProperty(value = "可见类型 0所有人 1登录可见 2密码可见")
    private Integer permission;

    @ApiModelProperty(value = "访问密码")
    private String password;

    @ApiModelProperty(value = "验证密码(前端回传)")
    @TableField(exist = false)
    private String password2;

    @ApiModelProperty(value = "位置 0不限 1顶部 2底部")
    private Integer position;

    @ApiModelProperty(value = "仅在顶部显示")
    private Integer top;

    @ApiModelProperty(value = "仅在底部显示")
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

    @ApiModelProperty(value = "父级栏目位置")
    @TableField(exist = false)
    private Integer parentPosition;

    @ApiModelProperty(value = "模型名称")
    private String modelName;

    @ApiModelProperty(value = "绑定的页面（已废弃）")
    private Integer pageId;

    @ApiModelProperty(value = "详情页ID")
    private Integer itemId;

    @ApiModelProperty(value = "是否微信小程序菜单")
    private Boolean isMpWeixin;

    @ApiModelProperty(value = "菜单间距")
    private Integer gutter;

    @ApiModelProperty(value = "菜单宽度")
    private Integer span;

    @ApiModelProperty(value = "阅读量")
    private Integer readNum;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "国际化语言")
    private String lang;

    @ApiModelProperty(value = "用于同步翻译内容")
    private Integer langCategoryId;

    @ApiModelProperty(value = "设为首页")
    private Integer home;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;

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
    private Date createTime;


    @ApiModelProperty(value = "页面名称")
    @TableField(exist = false)
    private String pageName;

    @ApiModelProperty("子菜单")
    @TableField(exist = false)
    private List<CmsNavigation> children;

    @ApiModelProperty(value = "页面布局")
    @TableField(exist = false)
    private String layout;

    @ApiModelProperty(value = "关联的页面")
    @TableField(exist = false)
    private CmsDesign design;

    @ApiModelProperty(value = "所属模型")
    @TableField(exist = false)
    private CmsModel modelInfo;

    @ApiModelProperty(value = "父级栏目")
    @TableField(exist = false)
    private CmsNavigation parent;

    @ApiModelProperty(value = "当前栏目名称")
    @TableField(exist = false)
    private String categoryName;

    @ApiModelProperty(value = "当前栏目链接")
    @TableField(exist = false)
    private String categoryPath;

    @ApiModelProperty(value = "栏目图片")
    @TableField(exist = false)
    private String photo;

    @ApiModelProperty(value = "是否开启布局")
    @TableField(exist = false)
    private Boolean showLayout;

    @ApiModelProperty(value = "单页内容")
    @TableField(exist = false)
    private String content;

    @ApiModelProperty(value = "是否显示banner")
    @TableField(exist = false)
    private Boolean showBanner;

    @ApiModelProperty(value = "菜单标题")
    @TableField(exist = false)
    private String text;

    public String getCategoryName() {
      return this.title;
    }

    public String getCategoryPath() {
      return this.path;
    }

    public String getText() {
      return this.title;
    }

}
