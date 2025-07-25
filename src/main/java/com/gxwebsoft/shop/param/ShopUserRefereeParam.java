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
 * 用户推荐关系表查询参数
 *
 * @author 科技小王子
 * @since 2025-03-05 17:05:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopUserRefereeParam对象", description = "用户推荐关系表查询参数")
public class ShopUserRefereeParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "推荐人ID")
    @QueryField(type = QueryType.EQ)
    private Integer dealerId;

    @ApiModelProperty(value = "用户id(被推荐人)")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "推荐关系层级(1,2,3)")
    @QueryField(type = QueryType.EQ)
    private Integer level;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
