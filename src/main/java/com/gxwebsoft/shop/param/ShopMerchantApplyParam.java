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
 * 商户入驻申请查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopMerchantApplyParam对象", description = "商户入驻申请查询参数")
public class ShopMerchantApplyParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer applyId;

    @ApiModelProperty(value = "类型")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "店铺类型")
    private String shopType;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "门店图片")
    private String image;

    @ApiModelProperty(value = "商户手机号")
    private String phone;

    @ApiModelProperty(value = "商户姓名")
    private String realName;

    @ApiModelProperty(value = "商户行业分类ID")
    @QueryField(type = QueryType.EQ)
    private Integer categoryId;

    @ApiModelProperty(value = "商户分类")
    private String category;

    @ApiModelProperty(value = "经纬度")
    private String lngAndLat;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "地区ID")
    private String regionId;

    @ApiModelProperty(value = "手续费")
    @QueryField(type = QueryType.EQ)
    private BigDecimal commission;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "营业执照")
    private String yyzz;

    @ApiModelProperty(value = "身份证")
    private String sfz1;

    @ApiModelProperty(value = "身份证")
    private String sfz2;

    @ApiModelProperty(value = "资质图片")
    private String files;

    @ApiModelProperty(value = "所有人")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "是否自营")
    @QueryField(type = QueryType.EQ)
    private Integer ownStore;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Integer recommend;

    @ApiModelProperty(value = "是否需要审核")
    @QueryField(type = QueryType.EQ)
    private Integer goodsReview;

    @ApiModelProperty(value = "工作负责人")
    private String name2;

    @ApiModelProperty(value = "驳回原因")
    private String reason;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

}
