package com.gxwebsoft.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 *
 * @author WebSoft
 * @since 2021-08-30 17:35:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "登录参数")
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("短信验证码")
    private String code;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
