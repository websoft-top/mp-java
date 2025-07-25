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
 * 分红角色
 *
 * @author 科技小王子
 * @since 2025-05-01 10:01:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopCommissionRole对象", description = "分红角色")
public class ShopCommissionRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private Integer provinceId;

    private Integer cityId;

    private Integer regionId;

    @ApiModelProperty(value = "状态, 0正常, 1异常")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    private Integer sortNumber;

}
