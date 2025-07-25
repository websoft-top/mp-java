package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分销商设置表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopDealerSetting对象", description = "分销商设置表")
public class ShopDealerSetting implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设置项标示")
    @TableId(value = "key", type = IdType.AUTO)
    private String key;

    @ApiModelProperty(value = "设置项描述")
    private String describe;

    @ApiModelProperty(value = "设置内容(json格式)")
    private String values;

    @ApiModelProperty(value = "商城ID")
    private Integer tenantId;

    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

}
