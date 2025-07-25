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
 * 聊天消息表
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopChatMessage对象", description = "聊天消息表")
public class ShopChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "发送人ID")
    private Integer formUserId;

    @ApiModelProperty(value = "接收人ID")
    private Integer toUserId;

    @ApiModelProperty(value = "消息类型")
    private String type;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "屏蔽接收方")
    private Integer sideTo;

    @ApiModelProperty(value = "屏蔽发送方")
    private Integer sideFrom;

    @ApiModelProperty(value = "是否撤回")
    private Integer withdraw;

    @ApiModelProperty(value = "文件信息")
    private String fileInfo;

    @ApiModelProperty(value = "存在联系方式")
    private Integer hasContact;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "状态, 0未读, 1已读")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
