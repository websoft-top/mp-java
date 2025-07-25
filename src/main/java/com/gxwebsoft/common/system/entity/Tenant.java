package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 租户
 *
 * @author 科技小王子
 * @since 2023-07-17 17:49:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Tenant对象", description = "租户")
@TableName("sys_tenant")
public class Tenant implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "租户id")
  @TableId(value = "tenant_id", type = IdType.AUTO)
  private Integer tenantId;

  @ApiModelProperty(value = "租户名称")
  private String tenantName;

  @ApiModelProperty(value = "租户编号")
  private String tenantCode;

  @ApiModelProperty(value = "备注")
  private String comments;

  @ApiModelProperty(value = "状态")
  private Integer status;

  @ApiModelProperty(value = "用户ID")
  private Integer userId;

  @ApiModelProperty(value = "是否删除, 0否, 1是")
  @TableLogic
  private Integer deleted;

  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  @ApiModelProperty(value = "修改时间")
  private Date updateTime;

  @ApiModelProperty(value = "logo")
  @TableField(exist = false)
  private String logo;

  @ApiModelProperty(value = "游客")
  @TableField(exist = false)
  private String username;

  @ApiModelProperty(value = "游客身份")
  @TableField(exist = false)
  private String token;

  @ApiModelProperty(value = "当前登录用户")
  @TableField(exist = false)
  private User loginUser;

  @ApiModelProperty(value = "菜单信息")
  @TableField(exist = false)
  private List<Menu> menu;

}
