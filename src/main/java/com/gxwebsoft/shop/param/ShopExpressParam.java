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
 * 物流公司查询参数
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopExpressParam对象", description = "物流公司查询参数")
public class ShopExpressParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流公司ID")
    @QueryField(type = QueryType.EQ)
    private Integer expressId;

    @ApiModelProperty(value = "物流公司名称")
    private String expressName;

    @ApiModelProperty(value = "物流公司编码 (微信)")
    private String wxCode;

    @ApiModelProperty(value = "物流公司编码 (快递100)")
    private String kuaidi100Code;

    @ApiModelProperty(value = "物流公司编码 (快递鸟)")
    private String kdniaoCode;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
