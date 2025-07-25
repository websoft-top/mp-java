package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规格
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsProductSpec对象", description = "规格")
public class CmsProductSpec implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规格ID")
    @TableId(value = "spec_id", type = IdType.AUTO)
    private Integer specId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格值")
    private String specValue;

    @ApiModelProperty(value = "创建用户")
    private Integer userId;

    @ApiModelProperty(value = "更新者")
    private Integer updater;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1待修,2异常已修，3异常未修")
    private Integer status;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
