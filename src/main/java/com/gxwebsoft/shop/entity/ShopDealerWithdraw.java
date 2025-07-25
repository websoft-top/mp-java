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
 * 分销商提现明细表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopDealerWithdraw对象", description = "分销商提现明细表")
public class ShopDealerWithdraw implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分销商用户ID")
    private Integer userId;

    @ApiModelProperty(value = "提现金额")
    private BigDecimal money;

    @ApiModelProperty(value = "打款方式 (10微信 20支付宝 30银行卡)")
    private Integer payType;

    @ApiModelProperty(value = "支付宝姓名")
    private String alipayName;

    @ApiModelProperty(value = "支付宝账号")
    private String alipayAccount;

    @ApiModelProperty(value = "开户行名称")
    private String bankName;

    @ApiModelProperty(value = "银行开户名")
    private String bankAccount;

    @ApiModelProperty(value = "银行卡号")
    private String bankCard;

    @ApiModelProperty(value = "申请状态 (10待审核 20审核通过 30驳回 40已打款)")
    private Integer applyStatus;

    @ApiModelProperty(value = "审核时间")
    private Integer auditTime;

    @ApiModelProperty(value = "驳回原因")
    private String rejectReason;

    @ApiModelProperty(value = "来源客户端(APP、H5、小程序等)")
    private String platform;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
