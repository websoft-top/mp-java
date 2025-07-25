package com.gxwebsoft.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传记录查询参数
 *
 * @author WebSoft
 * @since 2021-08-30 11:29:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "文件上传记录查询参数")
public class FileRecordParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @QueryField(type = QueryType.EQ)
    @ApiModelProperty("主键id")
    private Integer id;

    @QueryField(type = QueryType.EQ)
    @ApiModelProperty("分组ID")
    private String groupId;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件存储路径")
    private String path;

    @QueryField(type = QueryType.EQ)
    @ApiModelProperty("创建人")
    private Integer createUserId;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("文件类型")
    private String contentType;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("创建人账号")
    @TableField(exist = false)
    private String createUsername;

    @ApiModelProperty("创建人名称")
    @TableField(exist = false)
    private String createNickname;

    @ApiModelProperty("用户头像")
    @TableField(exist = false)
    private String avatar;

    @ApiModelProperty("商户编号")
    private String merchantCode;

}
