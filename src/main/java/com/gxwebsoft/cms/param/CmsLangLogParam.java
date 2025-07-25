package com.gxwebsoft.cms.param;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 国际化记录启用查询参数
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsLangLogParam对象", description = "国际化记录启用查询参数")
public class CmsLangLogParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String lang;

    @ApiModelProperty(value = "关联ID")
    @QueryField(type = QueryType.EQ)
    private Integer langId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "创建者UID")
    @TableField(exist = false)
    private Integer websiteUserId;
}
