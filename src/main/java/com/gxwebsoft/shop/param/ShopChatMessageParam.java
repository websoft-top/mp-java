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
 * 聊天消息表查询参数
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ShopChatMessageParam对象", description = "聊天消息表查询参数")
public class ShopChatMessageParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "发送人ID")
    @QueryField(type = QueryType.EQ)
    private Integer formUserId;

    @ApiModelProperty(value = "接收人ID")
    @QueryField(type = QueryType.EQ)
    private Integer toUserId;

    @ApiModelProperty(value = "消息类型")
    private String type;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "屏蔽接收方")
    @QueryField(type = QueryType.EQ)
    private Integer sideTo;

    @ApiModelProperty(value = "屏蔽发送方")
    @QueryField(type = QueryType.EQ)
    private Integer sideFrom;

    @ApiModelProperty(value = "是否撤回")
    @QueryField(type = QueryType.EQ)
    private Integer withdraw;

    @ApiModelProperty(value = "文件信息")
    private String fileInfo;

    @ApiModelProperty(value = "存在联系方式")
    @QueryField(type = QueryType.EQ)
    private Integer hasContact;

    @ApiModelProperty(value = "排序号")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0未读, 1已读")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

}
