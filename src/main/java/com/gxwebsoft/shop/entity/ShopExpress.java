package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物流公司
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopExpress对象", description = "物流公司")
public class ShopExpress implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流公司ID")
    @TableId(value = "express_id", type = IdType.AUTO)
    private Integer expressId;

    @ApiModelProperty(value = "物流公司名称")
    private String expressName;

    @ApiModelProperty(value = "物流公司编码 (微信)")
    private String wxCode;

    @ApiModelProperty(value = "物流公司编码 (快递100)")
    private String kuaidi100Code;

    @ApiModelProperty(value = "物流公司编码 (快递鸟)")
    private String kdniaoCode;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
