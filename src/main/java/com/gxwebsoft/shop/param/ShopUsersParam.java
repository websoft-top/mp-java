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
 * 查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopUsersParam对象", description = "查询参数")
public class ShopUsersParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
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
    @QueryField(type = QueryType.EQ)
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
    @QueryField(type = QueryType.EQ)
    private BigDecimal integral;

    @ApiModelProperty(value = "余额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal money;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    private String idCard;

    private String realName;

    @ApiModelProperty(value = "是否管理员：1是；2否")
    @QueryField(type = QueryType.EQ)
    private Boolean isAdmin;

}
