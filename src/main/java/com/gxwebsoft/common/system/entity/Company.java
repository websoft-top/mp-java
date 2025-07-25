package com.gxwebsoft.common.system.entity;

import cn.hutool.core.util.DesensitizedUtil;
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
 * 企业信息
 *
 * @author 科技小王子
 * @since 2023-05-27 14:57:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Company对象", description = "企业信息")
@TableName("sys_company")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业id")
    @TableId(value = "company_id", type = IdType.AUTO)
    private Integer companyId;

    @ApiModelProperty(value = "应用类型")
    private Integer type;

    @ApiModelProperty(value = "企业简称")
    private String shortName;

    @ApiModelProperty(value = "企业全称")
    @TableField(exist = false)
    private String companyName;

    @ApiModelProperty(value = "企业标识")
    private String companyCode;

    @ApiModelProperty(value = "企业类型")
    private String companyType;

    @ApiModelProperty(value = "是否官方")
    private Boolean official;

    @ApiModelProperty(value = "企业类型 多选")
    private String companyTypeMultiple;

    @ApiModelProperty(value = "应用标识")
    private String companyLogo;

    @ApiModelProperty(value = "封面图")
    private String image;

    @ApiModelProperty(value = "应用详情")
    @TableField(exist = false)
    private String content;

    @ApiModelProperty(value = "栏目分类")
    private Integer categoryId;

    @ApiModelProperty(value = "栏目名称")
    @TableField(exist = false)
    private String categoryName;

    @ApiModelProperty(value = "应用截图")
    private String files;

    @ApiModelProperty(value = "顶级域名")
    private String domain;

    @ApiModelProperty(value = "免费域名")
    private String freeDomain;

    @ApiModelProperty(value = "销售价格")
    private BigDecimal price;

    @ApiModelProperty(value = "计费方式")
    private Integer chargingMethod;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "公司座机")
    private String tel;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "企业法人")
    private String businessEntity;

    @ApiModelProperty(value = "发票抬头")
    @TableField("Invoice_header")
    private String invoiceHeader;

    @ApiModelProperty(value = "服务开始时间")
    private Date startTime;

    @ApiModelProperty(value = "服务到期时间")
    private Date expirationTime;

    @ApiModelProperty(value = "即将过期")
    private Integer soon;

    @ApiModelProperty(value = "应用版本 10体验版 20授权版 30旗舰版")
    private Integer version;

    @ApiModelProperty(value = "版本名称")
    private String versionName;

    @ApiModelProperty(value = "版本号")
    private String versionCode;

    @ApiModelProperty(value = "评分")
    private BigDecimal rate;

    @ApiModelProperty(value = "企业成员(当前)")
    private Integer users;

    @ApiModelProperty(value = "成员数量(上限)")
    private Integer members;

    @ApiModelProperty(value = "浏览数量")
    private Long clicks;

    @ApiModelProperty(value = "点赞数量")
    private Long likes;

    @ApiModelProperty(value = "购买数量")
    private Long buys;

    @ApiModelProperty(value = "存储空间")
    private Long storage;

    @ApiModelProperty(value = "存储空间(上限)")
    private Long storageMax;

    @ApiModelProperty(value = "行业类型(父级)")
    private String industryParent;

    @ApiModelProperty(value = "行业类型(子级)")
    private String industryChild;

    @ApiModelProperty(value = "部门数量")
    private Integer departments;

    @ApiModelProperty(value = "所在国家")
    private String country;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty(value = "街道地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否实名认证")
    private Integer authentication;

    @ApiModelProperty(value = "主控端节点")
    private String serverUrl;

    @ApiModelProperty(value = "模块节点")
    private String modulesUrl;

    @ApiModelProperty(value = "重定向节点")
    private String redirectUrl;

    @ApiModelProperty(value = "request合法域名")
    private String requestUrl;

    @ApiModelProperty(value = "socket合法域名")
    private String socketUrl;

    @ApiModelProperty(value = "总后台管理入口")
    private String adminUrl;

    @ApiModelProperty(value = "商户端管理入口")
    private String merchantUrl;

    @ApiModelProperty(value = "默认网站URL")
    private String websiteUrl;

    @ApiModelProperty(value = "微信小程序二维码")
    private String mpWeixinCode;

    @ApiModelProperty(value = "支付宝小程序二维码")
    private String mpAlipayCode;

    @ApiModelProperty(value = "H5端应用二维码")
    private String h5Code;

    @ApiModelProperty(value = "安卓APP二维码")
    private String androidUrl;

    @ApiModelProperty(value = "苹果APP二维码")
    private String iosUrl;

    @ApiModelProperty(value = "应用类型")
    private String appType;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sortNumber;

    @ApiModelProperty(value = "插件ID(菜单根节点)")
    private Integer menuId;

    @ApiModelProperty(value = "当前使用的租户模板")
    private Integer planId;

    @ApiModelProperty(value = "是否开启网站")
    private Boolean websiteStatus;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "是否含税")
    private Boolean isTax;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否默认企业主体")
    private Boolean authoritative;

    @ApiModelProperty("是否推荐")
    private Boolean recommend;

    @ApiModelProperty("商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "租户ID")
    private Integer tid;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "租户名称")
    @TableField(exist = false)
    private String tenantName;

    @ApiModelProperty(value = "租户编号")
    @TableField(exist = false)
    private String tenantCode;

    @ApiModelProperty(value = "昵称")
    @TableField(exist = false)
    private String nickname;

    @ApiModelProperty(value = "配置信息")
    @TableField(exist = false)
    private Object config;

    @ApiModelProperty(value = "是否已收藏")
    @TableField(exist = false)
    private Boolean collection;

    @ApiModelProperty(value = "新注册的密码")
    @TableField(exist = false)
    private String password;

    @ApiModelProperty("手机号(脱敏)")
    @TableField(exist = false)
    private String mobile;

    @ApiModelProperty(value = "是否已购买")
    @TableField(exist = false)
    private Boolean isBuy;

    @ApiModelProperty(value = "用户是否已安装了该插件")
    @TableField(exist = false)
    private Boolean installed;

    @ApiModelProperty(value = "产品参数")
    @TableField(exist = false)
    private List<CompanyParameter> parameters;

    @ApiModelProperty(value = "产品按钮及链接")
    @TableField(exist = false)
    private List<CompanyUrl> links;

    @ApiModelProperty(value = "购买数量")
    @TableField(exist = false)
    private Integer num;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<Menu> authorities;

    @ApiModelProperty("记录克隆的模板ID")
    @TableField(exist = false)
    private Integer templateId;

    public String getMobile() {
      return DesensitizedUtil.mobilePhone(this.phone);
    }

}
