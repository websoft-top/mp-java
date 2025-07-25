package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 运费模板
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopExpressTemplate对象", description = "运费模板")
public class ShopExpressTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Boolean type;

    private String title;

    @ApiModelProperty(value = "收件价格")
    private BigDecimal firstAmount;

    @ApiModelProperty(value = "续件价格")
    private BigDecimal extraAmount;

    @ApiModelProperty(value = "状态, 0已发布, 1待审核 2已驳回 3违规内容")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    private Integer sortNumber;

    @ApiModelProperty(value = "首件数量/重量")
    private BigDecimal firstNum;

    @ApiModelProperty(value = "续件数量/重量")
    private BigDecimal extraNum;

}
