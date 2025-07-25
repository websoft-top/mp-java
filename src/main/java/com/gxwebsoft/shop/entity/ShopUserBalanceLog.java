package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
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
 * 用户余额变动明细表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopUserBalanceLog对象", description = "用户余额变动明细表")
public class ShopUserBalanceLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "余额变动场景(0下级下单1供应商收入2差价收益 10用户充值 20用户消费 30管理员操作 40订单退款)")
    private Integer scene;

    @ApiModelProperty(value = "变动金额")
    private BigDecimal money;

    @ApiModelProperty(value = "变动后余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "管理员备注")
    private String remark;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "操作人ID")
    private Integer adminId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "商户编码")
    private String merchantCode;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
