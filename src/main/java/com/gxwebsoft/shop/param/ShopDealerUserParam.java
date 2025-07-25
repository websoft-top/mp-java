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
 * 分销商用户记录表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopDealerUserParam对象", description = "分销商用户记录表查询参数")
public class ShopDealerUserParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "自增ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "支付密码")
    private String payPassword;

    @ApiModelProperty(value = "当前可提现佣金")
    @QueryField(type = QueryType.EQ)
    private BigDecimal money;

    @ApiModelProperty(value = "已冻结佣金")
    @QueryField(type = QueryType.EQ)
    private BigDecimal freezeMoney;

    @ApiModelProperty(value = "累积提现佣金")
    @QueryField(type = QueryType.EQ)
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "推荐人用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer refereeId;

    @ApiModelProperty(value = "成员数量(一级)")
    @QueryField(type = QueryType.EQ)
    private Integer firstNum;

    @ApiModelProperty(value = "成员数量(二级)")
    @QueryField(type = QueryType.EQ)
    private Integer secondNum;

    @ApiModelProperty(value = "成员数量(三级)")
    @QueryField(type = QueryType.EQ)
    private Integer thirdNum;

    @ApiModelProperty(value = "专属二维码")
    private String qrcode;

    @ApiModelProperty(value = "是否删除")
    @QueryField(type = QueryType.EQ)
    private Integer isDelete;

}
