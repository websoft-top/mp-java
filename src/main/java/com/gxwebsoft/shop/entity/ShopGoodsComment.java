package com.gxwebsoft.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopGoodsComment对象", description = "评论表")
public class ShopGoodsComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer uid;

    @ApiModelProperty(value = "订单ID")
    private Integer oid;

    @ApiModelProperty(value = "商品唯一id")
    private String unique;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "某种商品类型(普通商品、秒杀商品）")
    private String replyType;

    @ApiModelProperty(value = "商品分数")
    private Boolean goodsScore;

    @ApiModelProperty(value = "服务分数")
    private Boolean serviceScore;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "评论图片")
    private String pics;

    @ApiModelProperty(value = "管理员回复内容")
    private String merchantReplyContent;

    @ApiModelProperty(value = "管理员回复时间")
    private Integer merchantReplyTime;

    @ApiModelProperty(value = "0未删除1已删除")
    private Boolean isDel;

    @ApiModelProperty(value = "0未回复1已回复")
    private Boolean isReply;

    @ApiModelProperty(value = "用户名称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "商品规格属性值,多个,号隔开")
    private String sku;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
