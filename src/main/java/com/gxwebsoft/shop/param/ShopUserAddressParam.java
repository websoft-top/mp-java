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
 * 收货地址查询参数
 *
 * @author 科技小王子
 * @since 2025-07-22 23:06:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopUserAddressParam对象", description = "收货地址查询参数")
public class ShopUserAddressParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "所在国家")
    private String country;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "收货地址")
    private String fullAddress;

    private String lat;

    private String lng;

    @ApiModelProperty(value = "1先生 2女士")
    @QueryField(type = QueryType.EQ)
    private Integer gender;

    @ApiModelProperty(value = "家、公司、学校")
    private String type;

    @ApiModelProperty(value = "默认收货地址")
    @QueryField(type = QueryType.EQ)
    private Boolean isDefault;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

}
