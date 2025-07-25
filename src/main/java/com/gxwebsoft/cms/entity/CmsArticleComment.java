package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章评论表
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsArticleComment对象", description = "文章评论表")
public class CmsArticleComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价ID")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "文章ID")
    private Integer articleId;

    @ApiModelProperty(value = "评分 (10好评 20中评 30差评)")
    private Integer score;

    @ApiModelProperty(value = "评价内容")
    private String content;

    @ApiModelProperty(value = "是否为图片评价")
    private Integer isPicture;

    @ApiModelProperty(value = "评论者ID")
    private Integer userId;

    @ApiModelProperty(value = "被评价者ID")
    private Integer toUserId;

    @ApiModelProperty(value = "回复的评论ID")
    private Integer replyCommentId;

    @ApiModelProperty(value = "回复者ID")
    private Integer replyUserId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0未读, 1已读")
    private Integer status;

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
