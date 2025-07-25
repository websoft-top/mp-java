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
 * 文章分类表
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsArticleCategory对象", description = "文章分类表")
public class CmsArticleCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章分类ID")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty(value = "分类标识")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String title;

    @ApiModelProperty(value = "类型 0列表 1单页 2外链")
    private Integer type;

    @ApiModelProperty(value = "分类图片")
    private String image;

    @ApiModelProperty(value = "上级分类ID")
    private Integer parentId;

    @ApiModelProperty(value = "路由/链接地址")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "绑定的页面")
    private Integer pageId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "文章数量")
    private Integer count;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)")
    private Integer hide;

    @ApiModelProperty(value = "是否推荐")
    private Integer recommend;

    @ApiModelProperty(value = "是否显示在首页")
    private Integer showIndex;

    @ApiModelProperty(value = "状态, 0正常, 1禁用")
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
