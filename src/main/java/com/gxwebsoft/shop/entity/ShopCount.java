package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商城销售统计表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopCount对象", description = "商城销售统计表")
public class ShopCount implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "统计日期")
    private LocalDate dateTime;

    @ApiModelProperty(value = "总销售额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "今日销售额")
    private BigDecimal todayPrice;

    @ApiModelProperty(value = "总会员数")
    private BigDecimal totalUsers;

    @ApiModelProperty(value = "今日新增")
    private BigDecimal todayUsers;

    @ApiModelProperty(value = "总订单笔数")
    private BigDecimal totalOrders;

    @ApiModelProperty(value = "今日订单笔数")
    private BigDecimal todayOrders;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
