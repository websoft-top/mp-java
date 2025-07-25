package com.gxwebsoft.cms.entity;

import cn.hutool.core.util.DesensitizedUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网站信息记录表
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsWebsite对象", description = "网站信息记录表")
public class CmsWebsite implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "站点ID")
    @TableId(value = "website_id", type = IdType.AUTO)
    private Integer websiteId;

    @ApiModelProperty(value = "网站名称")
    private String websiteName;

    @ApiModelProperty(value = "网站标识")
    private String websiteCode;

    @ApiModelProperty(value = "网站LOGO")
    private String websiteIcon;

    @ApiModelProperty(value = "网站LOGO")
    private String websiteLogo;

    @ApiModelProperty(value = "网站LOGO(深色模式)")
    private String websiteDarkLogo;

    @ApiModelProperty(value = "网站类型")
    private String websiteType;

    @ApiModelProperty(value = "栏目ID")
    private Integer categoryId;

    @ApiModelProperty(value = "应用ID")
    @TableField(exist = false)
    private Integer appId;

    @ApiModelProperty(value = "网站截图")
    private String files;

    @ApiModelProperty(value = "网站类型 10企业官网 20微信小程序 30APP 40其他")
    private Integer type;

    @ApiModelProperty(value = "网站关键词")
    private String keywords;

    @ApiModelProperty(value = "域名前缀")
    private String prefix;

    @ApiModelProperty(value = "绑定域名")
    private String domain;

    @ApiModelProperty(value = "全局样式")
    private String style;

    @ApiModelProperty(value = "后台管理地址")
    private String adminUrl;

    @ApiModelProperty(value = "应用版本 10免费版 20授权版 30永久授权")
    private Integer version;

    @ApiModelProperty(value = "服务到期时间")
    private Date expirationTime;

    @ApiModelProperty(value = "是否到期")
    @TableField(exist = false)
    private Integer expired;

    @ApiModelProperty(value = "剩余天数")
    @TableField(exist = false)
    private Long expiredDays;

    @ApiModelProperty(value = "服务器ID")
    private Integer assetsId;

    @ApiModelProperty(value = "服务器ID")
    private String assetsName;

    @ApiModelProperty(value = "模版ID(存克隆的租户UID)")
    private Integer templateId;

    @ApiModelProperty(value = "模版名称")
    @TableField(exist = false)
    private String templateName;

    @ApiModelProperty(value = "行业类型(父级)")
    private String industryParent;

    @ApiModelProperty(value = "行业类型(子级)")
    private String industryChild;

    @ApiModelProperty(value = "企业ID")
    private Integer companyId;

    @ApiModelProperty(value = "开发者名称")
    private String developer;

    @ApiModelProperty(value = "所在国家")
    private String country;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "街道地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "ICP备案号")
    private String icpNo;

    @ApiModelProperty(value = "公安备案")
    private String policeNo;

    @ApiModelProperty(value = "网站描述")
    private String content;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "管理员备注")
    private String remarks;

    @ApiModelProperty(value = "是否推荐")
    private Integer recommend;

    @ApiModelProperty(value = "是否官方产品")
    private Boolean official;

    @ApiModelProperty(value = "允许展示到插件市场")
    private Boolean market;

    @ApiModelProperty(value = "是否插件类型 0应用 1插件")
    private Boolean plugin;

    @ApiModelProperty(value = "允许被搜索")
    private Boolean search;

    @ApiModelProperty(value = "主题色")
    private String color;

    @ApiModelProperty(value = "运行状态 0运行中 1已关闭 2维护中")
    private Integer running;

    @ApiModelProperty(value = "即将过期")
    private Integer soon;

    @ApiModelProperty(value = "评分")
    private BigDecimal rate;

    @ApiModelProperty(value = "点赞数量")
    private Integer likes;

    @ApiModelProperty(value = "点击数量")
    private Integer clicks;

    @ApiModelProperty(value = "购买数量")
    private Integer buys;

    @ApiModelProperty(value = "下载数量")
    private Integer downloads;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal price;

    @ApiModelProperty(value = "交付方式")
    private Integer deliveryMethod;

    @ApiModelProperty(value = "计费方式")
    private Integer chargingMethod;

    @ApiModelProperty(value = "状态 0未开通 1运行中 2维护中 3已关闭 4已欠费停机 5违规关停")
    private Integer status;

    @ApiModelProperty(value = "状态图标")
    @TableField(exist = false)
    private String statusIcon;

    @ApiModelProperty(value = "维护说明")
    private String statusText;

    @ApiModelProperty(value = "关闭说明")
    private String statusClose;

    @ApiModelProperty(value = "全局样式")
    private String styles;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "预设字段")
    @TableField(exist = false)
    private List<CmsWebsiteField> fields;

    @ApiModelProperty(value = "小程序导航图标")
    @TableField(exist = false)
    private Map<String, List<CmsMpMenu>> mpMenus;

    @ApiModelProperty(value = "网站导航栏")
    @TableField(exist = false)
    private List<CmsNavigation> navigations;

    @ApiModelProperty(value = "顶部菜单")
    @TableField(exist = false)
    private List<CmsNavigation> topNavs;

    @ApiModelProperty(value = "底部菜单")
    @TableField(exist = false)
    private List<CmsNavigation> bottomNavs;

    @ApiModelProperty(value = "幻灯片广告")
    @TableField(exist = false)
    private CmsAd slide;

    @ApiModelProperty(value = "站点广告")
    @TableField(exist = false)
    private List<CmsAd> ads;

    @ApiModelProperty(value = "首页布局")
    @TableField(exist = false)
    private String layout;

    @ApiModelProperty(value = "友情链接")
    @TableField(exist = false)
    private List<CmsLink> links;

    @ApiModelProperty(value = "配置信息")
    @TableField(exist = false)
    private Object config;

    @ApiModelProperty(value = "服务器时间")
    @TableField(exist = false)
    private HashMap<String, Object> serverTime;

    @ApiModelProperty(value = "当前登录用户")
    @TableField(exist = false)
    private User loginUser;

    @ApiModelProperty(value = "超管账号")
    @TableField(exist = false)
    private String superAdminPhone;

    @ApiModelProperty(value = "是否登录")
    @TableField(exist = false)
    private Boolean isLogin;

    @ApiModelProperty(value = "网站设置")
    @TableField(exist = false)
    private CmsWebsiteSetting setting;

    public String getPhone(){
      return DesensitizedUtil.mobilePhone(this.phone);
    }
}
