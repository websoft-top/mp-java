package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 国际化记录启用
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsLangLog对象", description = "国际化记录启用")
public class CmsLangLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String lang;

    @ApiModelProperty(value = "关联ID")
    private Integer langId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
