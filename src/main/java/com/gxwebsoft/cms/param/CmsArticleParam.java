package com.gxwebsoft.cms.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Set;

/**
 * 文章查询参数
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmsArticleParam对象", description = "文章查询参数")
public class CmsArticleParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    @QueryField(type = QueryType.EQ)
    private Integer articleId;

    @ApiModelProperty(value = "父级栏目ID")
    @TableField(exist = false)
    private Set<Integer> categoryIds;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章类型 0常规 1视频")
    @QueryField(type = QueryType.EQ)
    private Integer type;

    @ApiModelProperty(value = "文章模型")
    @QueryField(type = QueryType.EQ)
    private String model;

    @ApiModelProperty(value = "详情页标识")
    @QueryField(type = QueryType.EQ)
    private String detail;

    @ApiModelProperty(value = "列表显示方式(10小图展示 20大图展示)")
    @QueryField(type = QueryType.EQ)
    private Integer showType;

    @ApiModelProperty(value = "话题")
    @QueryField(type = QueryType.LIKE)
    private String topic;

    @ApiModelProperty(value = "标签")
    @QueryField(type = QueryType.EQ)
    private String tags;

    @ApiModelProperty(value = "栏目ID")
    @QueryField(type = QueryType.EQ)
    private Integer navigationId;

    @ApiModelProperty(value = "文章分类ID")
    @QueryField(type = QueryType.EQ)
    private Integer categoryId;

    @ApiModelProperty(value = "父级栏目ID")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty(value = "封面图")
    private String image;

    @ApiModelProperty(value = "是否包含封面图")
    @QueryField(type = QueryType.EQ)
    private Boolean hasImage;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "虚拟阅读量(仅用作展示)")
    @QueryField(type = QueryType.EQ)
    private Integer virtualViews;

    @ApiModelProperty(value = "实际阅读量")
    @QueryField(type = QueryType.EQ)
    private Integer actualViews;

    @ApiModelProperty(value = "可见类型 0所有人 1登录可见 2密码可见")
    @QueryField(type = QueryType.EQ)
    private Integer permission;

    @ApiModelProperty(value = "访问密码")
    @QueryField(type = QueryType.EQ)
    private String password;

    @ApiModelProperty(value = "访问密码")
    @QueryField(type = QueryType.EQ)
    private String password2;

    @ApiModelProperty(value = "发布来源客户端 (APP、H5、小程序等)")
    private String platform;

    @ApiModelProperty(value = "文章附件")
    private String files;

    @ApiModelProperty(value = "视频地址")
    private String video;

    @ApiModelProperty(value = "接受的文件类型")
    private String accept;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty(value = "街道地址")
    private String address;

    @ApiModelProperty(value = "点赞数")
    @QueryField(type = QueryType.EQ)
    private Integer likes;

    @ApiModelProperty(value = "评论数")
    @QueryField(type = QueryType.EQ)
    private Integer commentNumbers;

    @ApiModelProperty(value = "提醒谁看")
    private String toUsers;

    @ApiModelProperty(value = "用户ID")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty(value = "项目ID")
    @QueryField(type = QueryType.EQ)
    private Long projectId;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    @QueryField(type = QueryType.EQ)
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0已发布, 1待审核 2已驳回 3违规内容")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer deleted;

    @ApiModelProperty(value = "是否推荐")
    @QueryField(type = QueryType.EQ)
    private Integer recommend;

    @ApiModelProperty(value = "文章ID集查询")
    @TableField(exist = false)
    private Set<Integer> articleIds;

    @ApiModelProperty(value = "网站创建者ID")
    @QueryField(type = QueryType.EQ)
    private Integer websiteUserId;

}
