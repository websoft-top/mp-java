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
public class AlipayParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("支付宝授权码")
    private String authCode;

    @ApiModelProperty("登录账号")
    private String username;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
