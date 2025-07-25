package com.gxwebsoft.cms.param;

import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章评论表查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsArticleCommentParam对象", description = "文章评论表查询参数")
public class CmsArticleCommentParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价ID")
    @QueryField(type = QueryType.EQ)
    private Integer commentId;

    @ApiModelProperty(value = "文章ID")
    @QueryField(type = QueryType.EQ)
    private Integer articleId;

    @ApiModelProperty(value = "评分 (10好评 20中评 30差评)")
    @QueryField(type = QueryType.EQ)
    private Integer score;

    @ApiModelProperty(value = "评价内容")
    private String content;

    @ApiModelProperty(value = "是否为图片评价")
    @QueryField(type = QueryType.EQ)
    private Integer isPicture;

    @ApiModelProperty(value = "评论者ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "被评价者ID")
    @QueryField(type = QueryType.EQ)
    private Integer toUserId;

    @ApiModelProperty(value = "回复的评论ID")
    @QueryField(type = QueryType.EQ)
    private Integer replyCommentId;

    @ApiModelProperty(value = "回复者ID")
    @QueryField(type = QueryType.EQ)
    private Integer replyUserId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0未读, 1已读")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
