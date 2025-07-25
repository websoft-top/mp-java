package com.gxwebsoft.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 缓存管理
 *
 * @author WebSoft
 * @since 2021-08-30 17:35:16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "缓存管理")
public class CacheParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("key")
    private String key;

}
