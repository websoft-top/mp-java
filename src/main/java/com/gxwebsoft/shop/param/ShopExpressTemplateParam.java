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
 * 运费模板查询参数
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopExpressTemplateParam对象", description = "运费模板查询参数")
public class ShopExpressTemplateParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    @QueryField(type = QueryType.EQ)
    private Boolean type;

    private String title;

    @ApiModelProperty(value = "收件价格")
    @QueryField(type = QueryType.EQ)
    private BigDecimal firstAmount;

    @ApiModelProperty(value = "续件价格")
    @QueryField(type = QueryType.EQ)
    private BigDecimal extraAmount;

    @ApiModelProperty(value = "状态, 0已发布, 1待审核 2已驳回 3违规内容")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "首件数量/重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal firstNum;

    @ApiModelProperty(value = "续件数量/重量")
    @QueryField(type = QueryType.EQ)
    private BigDecimal extraNum;

}
