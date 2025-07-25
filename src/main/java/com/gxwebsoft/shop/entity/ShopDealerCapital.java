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
 * 分销商资金明细表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopDealerCapital对象", description = "分销商资金明细表")
public class ShopDealerCapital implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分销商用户ID")
    private Integer userId;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "资金流动类型 (10佣金收入 20提现支出 30转账支出 40转账收入)")
    private Integer flowType;

    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "对方用户ID")
    private Integer toUserId;

    @ApiModelProperty(value = "商城ID")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
