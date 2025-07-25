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
 * 商户账号查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopMerchantAccountParam对象", description = "商户账号查询参数")
public class ShopMerchantAccountParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "商户手机号")
    private String phone;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "商户ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "角色ID")
    @QueryField(type = QueryType.EQ)
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

}
