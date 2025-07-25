package com.gxwebsoft.cms.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 网站信息记录表查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsWebsiteParam对象", description = "网站信息记录表查询参数")
public class CmsWebsiteParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "站点ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteId;

    @ApiModelProperty(value = "站点类型")
    @QueryField(type = QueryType.EQ)
    private Integer type;

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
    @QueryField(type = QueryType.EQ)
    private Integer categoryId;

    @ApiModelProperty(value = "网站截图")
    private String files;

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
    @QueryField(type = QueryType.EQ)
    private Integer version;

    @ApiModelProperty(value = "服务到期时间")
    private String expirationTime;

    @ApiModelProperty(value = "模版ID")
    @QueryField(type = QueryType.EQ)
    private Integer templateId;

    @ApiModelProperty(value = "行业类型(父级)")
    private String industryParent;

    @ApiModelProperty(value = "行业类型(子级)")
    private String industryChild;

    @ApiModelProperty(value = "企业ID")
    @QueryField(type = QueryType.EQ)
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

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Integer recommend;

    @ApiModelProperty(value = "是否官方产品")
    @QueryField(type = QueryType.EQ)
    private Boolean official;

    @ApiModelProperty(value = "是否查询超管账号")
    @QueryField(type = QueryType.EQ)
    private Boolean showAdminPhone;

    @ApiModelProperty(value = "允许展示到插件市场")
    @QueryField(type = QueryType.EQ)
    private Boolean market;

    @ApiModelProperty(value = "是否插件类型 0应用 1插件")
    @QueryField(type = QueryType.EQ)
    private Boolean plugin;

    @ApiModelProperty(value = "允许被搜索")
    @QueryField(type = QueryType.EQ)
    private Boolean search;

    @ApiModelProperty(value = "主题色")
    private String color;

    @ApiModelProperty(value = "点赞数量")
    private Integer likes;

    @ApiModelProperty(value = "点击数量")
    private Integer clicks;

    @ApiModelProperty(value = "购买数量")
    private Integer buys;

    @ApiModelProperty(value = "下载数量")
    private Integer downloads;

    @ApiModelProperty(value = "状态 0未开通 1运行中 2维护中 3已关闭 4已欠费停机 5违规关停")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "维护说明")
    private String statusText;

    @ApiModelProperty(value = "关闭说明")
    private String statusClose;

    @ApiModelProperty(value = "全局样式")
    private String styles;

    @ApiModelProperty(value = "运行状态")
    @QueryField(type = QueryType.EQ)
    private Integer running;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "按userId集搜索")
    @QueryField(type = QueryType.EQ)
    private Set<Integer> userIds;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "按WebsiteIds集搜索")
    private Set<Integer> websiteIds;

    @ApiModelProperty(value = "当前登录用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer loginUserId;

    @ApiModelProperty(value = "管理员手机号")
    @QueryField(type = QueryType.EQ)
    private String adminPhone;

}
