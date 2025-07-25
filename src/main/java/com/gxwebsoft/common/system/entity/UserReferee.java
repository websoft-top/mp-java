package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户推荐关系表
 *
 * @author 科技小王子
 * @since 2023-10-07 22:56:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserReferee对象", description = "用户推荐关系表")
@TableName("sys_user_referee")
public class UserReferee implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "推荐人ID")
    private Integer dealerId;

    @ApiModelProperty(value = "用户id(被推荐人)")
    private Integer userId;

    @ApiModelProperty(value = "推荐关系层级(1,2,3)")
    private Integer level;

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

    @TableField(exist = false)
    private User user;
}
