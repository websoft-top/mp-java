package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopUsers对象", description = "")
public class ShopUsers implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户唯一小程序id")
    private String openId;

    @ApiModelProperty(value = "小程序用户秘钥")
    private String sessionKey;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "1男，2女")
    private Boolean gender;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "积分")
    private BigDecimal integral;

    @ApiModelProperty(value = "余额")
    private BigDecimal money;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "注册时间")
    private Integer createTime;

    private String idCard;

    private String realName;

    @ApiModelProperty(value = "是否管理员：1是；2否")
    private Boolean isAdmin;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
