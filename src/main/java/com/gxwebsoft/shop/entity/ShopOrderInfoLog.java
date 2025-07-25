package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单核销
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopOrderInfoLog对象", description = "订单核销")
public class ShopOrderInfoLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联订单表id")
    private Integer orderId;

    @ApiModelProperty(value = "关联商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "关联场地id")
    private Integer fieldId;

    @ApiModelProperty(value = "核销数量")
    private Boolean useNum;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
