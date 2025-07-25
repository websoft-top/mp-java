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
 * 分销商申请记录表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopDealerApplyParam对象", description = "分销商申请记录表查询参数")
public class ShopDealerApplyParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer applyId;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "推荐人用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer refereeId;

    @ApiModelProperty(value = "申请方式(10需后台审核 20无需审核)")
    @QueryField(type = QueryType.EQ)
    private Integer applyType;

    @ApiModelProperty(value = "申请时间")
    @QueryField(type = QueryType.EQ)
    private Integer applyTime;

    @ApiModelProperty(value = "审核状态 (10待审核 20审核通过 30驳回)")
    @QueryField(type = QueryType.EQ)
    private Integer applyStatus;

    @ApiModelProperty(value = "审核时间")
    @QueryField(type = QueryType.EQ)
    private Integer auditTime;

    @ApiModelProperty(value = "驳回原因")
    private String rejectReason;

}
