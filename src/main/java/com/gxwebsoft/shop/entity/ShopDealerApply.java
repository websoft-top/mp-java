package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分销商申请记录表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopDealerApply对象", description = "分销商申请记录表")
public class ShopDealerApply implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "apply_id", type = IdType.AUTO)
    private Integer applyId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "推荐人用户ID")
    private Integer refereeId;

    @ApiModelProperty(value = "申请方式(10需后台审核 20无需审核)")
    private Integer applyType;

    @ApiModelProperty(value = "申请时间")
    private Integer applyTime;

    @ApiModelProperty(value = "审核状态 (10待审核 20审核通过 30驳回)")
    private Integer applyStatus;

    @ApiModelProperty(value = "审核时间")
    private Integer auditTime;

    @ApiModelProperty(value = "驳回原因")
    private String rejectReason;

    @ApiModelProperty(value = "商城ID")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
