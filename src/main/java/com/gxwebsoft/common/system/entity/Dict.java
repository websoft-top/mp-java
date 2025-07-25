package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 字典
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:03
 */
@Data
@ApiModel(description = "字典(业务类)")
@TableName("sys_dict")
public class Dict implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典id")
    @TableId(type = IdType.AUTO)
    private Integer dictId;

    @ApiModelProperty(value = "字典标识")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "字典项列表")
    @TableField(exist = false)
    private Set<Set<String>> items;

}
