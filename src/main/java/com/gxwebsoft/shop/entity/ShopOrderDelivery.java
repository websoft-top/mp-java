package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发货单
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopOrderDelivery对象", description = "发货单")
public class ShopOrderDelivery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "发货单ID")
    @TableId(value = "delivery_id", type = IdType.AUTO)
    private Integer deliveryId;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "发货方式(10手动录入 20无需物流 30电子面单)")
    private Integer deliveryMethod;

    @ApiModelProperty(value = "打包方式(废弃)")
    private Integer packMethod;

    @ApiModelProperty(value = "物流公司ID")
    private Integer expressId;

    @ApiModelProperty(value = "物流单号")
    private String expressNo;

    @ApiModelProperty(value = "电子面单模板内容")
    private String eorderHtml;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
