package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;

/**
 * 机构
 *
 * @author LX
 * @since 2025-04-14 00:35:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LawOrg对象", description = "机构")
@TableName("law_org")
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String query;

    @TableField(exist = false)
    private String inputs;

    @TableField(exist = false)
    private String responseMode;

    @TableField(exist = false)
    private String user;

    @TableField(exist = false)
    private String conversationId;

    @TableField(exist = false)
    private String type;

    @TableField(exist = false)
    private Integer requestType;

    @TableField(exist = false)
    private Map<String, Object> files;
}
