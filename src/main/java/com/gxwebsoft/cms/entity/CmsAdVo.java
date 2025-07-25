package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "图片DTO", description = "图片DTO")
public class CmsAdVo implements Serializable {

  @ApiModelProperty("ID")
  @TableField(exist = false)
  private Integer uid;

  @ApiModelProperty("名称")
  @TableField(exist = false)
  private String title;

  @ApiModelProperty("图片路径")
  @TableField(exist = false)
  private String url;

  @ApiModelProperty("视频地址")
  @TableField(exist = false)
  private String video;

  @ApiModelProperty("状态")
  @TableField(exist = false)
  private String status;

  @ApiModelProperty("图片宽")
  @TableField(exist = false)
  private Integer width;

  @ApiModelProperty("图片高")
  @TableField(exist = false)
  private Integer height;
}
