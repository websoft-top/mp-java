package com.gxwebsoft.common.system.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 短信验证码返回结果
 *
 * @author WebSoft
 * @since 2021-08-30 17:35:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "短信验证码返回结果")
public class SmsCaptchaResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("短信验证码")
    private String text;
}
