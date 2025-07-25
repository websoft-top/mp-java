package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.kuaidi100.sdk.request.samecity.OrderGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户推荐关系表
 *
 * @author 科技小王子
 * @since 2025-03-05 17:05:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopUserReferee对象", description = "用户推荐关系表")
public class ShopUserReferee implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "推荐人ID")
    private Integer dealerId;

    @ApiModelProperty(value = "用户id(被推荐人)")
    private Integer userId;

    @ApiModelProperty(value = "推荐关系层级(1,2,3)")
    private Integer level;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    @ApiModelProperty(value = "被推荐人的报餐信息")
    @TableField(exist = false)
    private List<ShopOrder> order;

    @ApiModelProperty(value = "被推荐人的报餐菜品")
    @TableField(exist = false)
    private List<OrderGoods> orderGoods;

    @ApiModelProperty(value = "被推荐人昵称")
    @TableField(exist = false)
    private String nickname;

    @ApiModelProperty(value = "被推荐人头像")
    @TableField(exist = false)
    private String avatar;

    @ApiModelProperty(value = "被推荐人等级ID")
    @TableField(exist = false)
    private Integer gradeId;

    @ApiModelProperty(value = "被推荐人等级")
    @TableField(exist = false)
    private String gradeName;

    @ApiModelProperty(value = "推荐人昵称")
    @TableField(exist = false)
    private String dealerName;

    @ApiModelProperty(value = "推荐人头像")
    @TableField(exist = false)
    private String dealerAvatar;

    @ApiModelProperty(value = "推荐人电话")
    @TableField(exist = false)
    private String dealerPhone;

    @ApiModelProperty(value = "用户所属门店")
    private Long merchantId;

    @ApiModelProperty(value = "所属门店")
    @TableField(exist = false)
    private ShopMerchant merchant;

}
