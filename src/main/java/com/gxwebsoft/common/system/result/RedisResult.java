package com.gxwebsoft.common.system.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Redis缓存数据
 *
 * @author WebSoft
 * @since 2021-08-30 17:35:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "缓存数据返回")
public class RedisResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("key")
    private String key;

    @ApiModelProperty("数据")
    private T data;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

}
