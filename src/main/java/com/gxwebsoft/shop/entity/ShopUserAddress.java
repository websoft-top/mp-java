package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收货地址
 *
 * @author 科技小王子
 * @since 2025-07-22 23:06:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopUserAddress对象", description = "收货地址")
public class ShopUserAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
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
    private Integer gender;

    @ApiModelProperty(value = "家、公司、学校")
    private String type;

    @ApiModelProperty(value = "默认收货地址")
    private Boolean isDefault;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

}
