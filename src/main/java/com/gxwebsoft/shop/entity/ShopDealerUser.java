package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分销商用户记录表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopDealerUser对象", description = "分销商用户记录表")
public class ShopDealerUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "自增ID")
    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "支付密码")
    private String payPassword;

    @ApiModelProperty(value = "当前可提现佣金")
    private BigDecimal money;

    @ApiModelProperty(value = "已冻结佣金")
    private BigDecimal freezeMoney;

    @ApiModelProperty(value = "累积提现佣金")
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "推荐人用户ID")
    private Integer refereeId;

    @ApiModelProperty(value = "成员数量(一级)")
    private Integer firstNum;

    @ApiModelProperty(value = "成员数量(二级)")
    private Integer secondNum;

    @ApiModelProperty(value = "成员数量(三级)")
    private Integer thirdNum;

    @ApiModelProperty(value = "专属二维码")
    private String qrcode;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
