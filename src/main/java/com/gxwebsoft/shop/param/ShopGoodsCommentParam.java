package com.gxwebsoft.shop.param;

import java.math.BigDecimal;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopGoodsCommentParam对象", description = "评论表查询参数")
public class ShopGoodsCommentParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer uid;

    @ApiModelProperty(value = "订单ID")
    @QueryField(type = QueryType.EQ)
    private Integer oid;

    @ApiModelProperty(value = "商品唯一id")
    private String unique;

    @ApiModelProperty(value = "商品id")
    @QueryField(type = QueryType.EQ)
    private Integer goodsId;

    @ApiModelProperty(value = "某种商品类型(普通商品、秒杀商品）")
    private String replyType;

    @ApiModelProperty(value = "商品分数")
    @QueryField(type = QueryType.EQ)
    private Boolean goodsScore;

    @ApiModelProperty(value = "服务分数")
    @QueryField(type = QueryType.EQ)
    private Boolean serviceScore;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "评论图片")
    private String pics;

    @ApiModelProperty(value = "管理员回复内容")
    private String merchantReplyContent;

    @ApiModelProperty(value = "管理员回复时间")
    @QueryField(type = QueryType.EQ)
    private Integer merchantReplyTime;

    @ApiModelProperty(value = "0未删除1已删除")
    @QueryField(type = QueryType.EQ)
    private Boolean isDel;

    @ApiModelProperty(value = "0未回复1已回复")
    @QueryField(type = QueryType.EQ)
    private Boolean isReply;

    @ApiModelProperty(value = "用户名称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "商品规格属性值,多个,号隔开")
    private String sku;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

}
