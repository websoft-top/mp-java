package com.gxwebsoft.shop.param;

import java.math.BigDecimal;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分销商提现明细表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopDealerWithdrawParam对象", description = "分销商提现明细表查询参数")
public class ShopDealerWithdrawParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "分销商用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "提现金额")
    @QueryField(type = QueryType.EQ)
    private BigDecimal money;

    @ApiModelProperty(value = "打款方式 (10微信 20支付宝 30银行卡)")
    @QueryField(type = QueryType.EQ)
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
    @QueryField(type = QueryType.EQ)
    private Integer applyStatus;

    @ApiModelProperty(value = "审核时间")
    @QueryField(type = QueryType.EQ)
    private Integer auditTime;

    @ApiModelProperty(value = "驳回原因")
    private String rejectReason;

    @ApiModelProperty(value = "来源客户端(APP、H5、小程序等)")
    private String platform;

}
