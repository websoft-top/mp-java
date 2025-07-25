package com.gxwebsoft.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 企业信息查询参数
 *
 * @author 科技小王子
 * @since 2023-05-27 14:57:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CompanyParam对象", description = "企业信息查询参数")
public class CompanyParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业id")
    @QueryField(type = QueryType.EQ)
    private Integer companyId;

    @ApiModelProperty(value = "应用类型")
    private Integer type;

    @ApiModelProperty(value = "是否官方")
    private Boolean official;

    @ApiModelProperty(value = "企业简称")
    private String shortName;

    @ApiModelProperty(value = "企业全称")
    private String companyName;

    @ApiModelProperty(value = "企业标识")
    private String companyCode;

    @ApiModelProperty(value = "类型 10企业 20政府单位")
    @QueryField(type = QueryType.EQ)
    private String companyType;

    @ApiModelProperty(value = "企业类型 多选")
    private String companyTypeMultiple;

    @ApiModelProperty(value = "应用标识")
    private String companyLogo;

    @ApiModelProperty(value = "栏目分类")
    @QueryField(type = QueryType.EQ)
    private Integer categoryId;

    @ApiModelProperty(value = "企业域名")
    private String domain;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    @QueryField(type = QueryType.EQ)
    private String email;

    @ApiModelProperty(value = "企业法人")
    private String businessEntity;

    @ApiModelProperty(value = "发票抬头")
    private String invoiceHeader;

    @ApiModelProperty(value = "服务开始时间")
    private String startTime;

    @ApiModelProperty(value = "服务到期时间")
    private String expirationTime;

    @ApiModelProperty(value = "应用版本 10体验版 20授权版 30旗舰版")
    @QueryField(type = QueryType.EQ)
    private Integer version;

    @ApiModelProperty(value = "成员数量")
    @QueryField(type = QueryType.EQ)
    private Integer members;

    @ApiModelProperty(value = "行业类型(父级)")
    private String industryParent;

    @ApiModelProperty(value = "行业类型(子级)")
    private String industryChild;

    @ApiModelProperty(value = "部门数量")
    @QueryField(type = QueryType.EQ)
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
    @QueryField(type = QueryType.EQ)
    private Integer authentication;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Boolean recommend;

    @ApiModelProperty(value = "应用类型 app应用 plug插件")
    private String appType;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty("商户ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "是否默认企业主体")
    @QueryField(type = QueryType.EQ)
    private Boolean authoritative;

    @ApiModelProperty(value = "租户号")
    private Integer tenantId;

    @ApiModelProperty(value = "应用名称")
    @QueryField(type = QueryType.EQ)
    private String appName;

    @ApiModelProperty(value = "企业id集合")
    @TableField(exist = false)
    private Set<Integer> companyIds;

}
