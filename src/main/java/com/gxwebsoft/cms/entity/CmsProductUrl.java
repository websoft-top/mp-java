package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 域名
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsProductUrl对象", description = "域名")
public class CmsProductUrl implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "产品ID")
    private Integer productId;

    @ApiModelProperty(value = "域名类型")
    private String type;

    @ApiModelProperty(value = "域名")
    private String domain;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0正常, 1待确认")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
