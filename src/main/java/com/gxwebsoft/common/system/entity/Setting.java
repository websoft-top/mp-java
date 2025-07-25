package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统设置
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Setting对象", description = "系统设置")
@TableName("sys_setting")
public class Setting implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "setting_id", type = IdType.AUTO)
    private Integer settingId;

    @ApiModelProperty(value = "设置项标示")
    private String settingKey;

    @ApiModelProperty(value = "设置内容（json格式）")
    private String content;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改租户名称")
    @TableField(exist = false)
    private String tenantName;

}
