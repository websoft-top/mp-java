package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分销商订单记录表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopDealerOrder对象", description = "分销商订单记录表")
public class ShopDealerOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "买家用户ID")
    private Integer userId;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "订单总金额(不含运费)")
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "分销商用户id(一级)")
    private Integer firstUserId;

    @ApiModelProperty(value = "分销商用户id(二级)")
    private Integer secondUserId;

    @ApiModelProperty(value = "分销商用户id(三级)")
    private Integer thirdUserId;

    @ApiModelProperty(value = "分销佣金(一级)")
    private BigDecimal firstMoney;

    @ApiModelProperty(value = "分销佣金(二级)")
    private BigDecimal secondMoney;

    @ApiModelProperty(value = "分销佣金(三级)")
    private BigDecimal thirdMoney;

    @ApiModelProperty(value = "订单是否失效(0未失效 1已失效)")
    private Integer isInvalid;

    @ApiModelProperty(value = "佣金结算(0未结算 1已结算)")
    private Integer isSettled;

    @ApiModelProperty(value = "结算时间")
    private Integer settleTime;

    @ApiModelProperty(value = "商城ID")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
