package com.gxwebsoft.common.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 缓存管理
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Setting对象", description = "缓存管理")
public class Cache implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "设置内容（json格式）")
    private String content;

    @ApiModelProperty(value = "过期时间(秒)")
    private Long expireTime;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

}
