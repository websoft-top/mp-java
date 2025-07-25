package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规格值
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopSpecValue对象", description = "规格值")
public class ShopSpecValue implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规格值ID")
    @TableId(value = "spec_value_id", type = IdType.AUTO)
    private Integer specValueId;

    @ApiModelProperty(value = "规格组ID")
    private Integer specId;

    @ApiModelProperty(value = "规格值")
    private String specValue;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
