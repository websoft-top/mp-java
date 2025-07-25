package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "阿里云机器翻译", description = "阿里云机器翻译")
public class TranslateVo implements Serializable {

  @ApiModelProperty("错误码")
  private String Code;

  @ApiModelProperty("错误信息")
  @JsonIgnoreProperties(ignoreUnknown = true)
  private String Message;

  @ApiModelProperty("请求ID")
  private String RequestId;

  @ApiModelProperty("返回数据")
  private TranslateDataVo Data;

}
