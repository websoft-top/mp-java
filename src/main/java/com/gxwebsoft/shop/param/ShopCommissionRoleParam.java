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
 * 分红角色查询参数
 *
 * @author 科技小王子
 * @since 2025-05-01 10:01:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopCommissionRoleParam对象", description = "分红角色查询参数")
public class ShopCommissionRoleParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    private Integer id;

    private String title;

    @QueryField(type = QueryType.EQ)
    private Integer provinceId;

    @QueryField(type = QueryType.EQ)
    private Integer cityId;

    @QueryField(type = QueryType.EQ)
    private Integer regionId;

    @ApiModelProperty(value = "状态, 0正常, 1异常")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String comments;

    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

}
