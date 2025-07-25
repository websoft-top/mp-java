package com.gxwebsoft.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 发送短信验证码参数
 *
 * @author WebSoft
 * @since 2021-08-30 17:35:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "发送短信验证码参数")
public class SmsCaptchaParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("短信签名")
    private String signName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("短信模板")
    private String TemplateParam;

}
