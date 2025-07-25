package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "翻译结果", description = "翻译结果")
public class TranslateDataVo implements Serializable {

  @ApiModelProperty("文本格式")
  private String FormatType;

  @ApiModelProperty("原文语言")
  private String SourceLanguage;

  @ApiModelProperty("译文语言")
  private String TargetLanguage;

  @ApiModelProperty("待翻译内容")
  private String SourceText;

  @ApiModelProperty("场景可选取值：商品标题（title），商品描述（description），商品沟通（communication），医疗（medical），社交（social)，金融（finance）")
  private String Scene;

  @ApiModelProperty("上下文信息")
  private String Context;

  @ApiModelProperty("翻译结果")
  private String translated;

  @ApiModelProperty("总单词数")
  private String wordCount;

  @ApiModelProperty("源语言传入 auto 时，语种识别后的源语言代码")
  private String detectedLanguage;

}
