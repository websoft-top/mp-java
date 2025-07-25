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
 * 分销商设置表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopDealerSettingParam对象", description = "分销商设置表查询参数")
public class ShopDealerSettingParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设置项标示")
    @QueryField(type = QueryType.EQ)
    private String key;

    @ApiModelProperty(value = "设置项描述")
    private String describe;

    @ApiModelProperty(value = "设置内容(json格式)")
    private String values;

}
