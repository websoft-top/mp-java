package com.gxwebsoft.cms.param;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户导入参数
 *
 * @author WebSoft
 * @since 2011-10-15 17:33:34
 */
@Data
public class CmsArticleImportParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "文章ID")
    private Integer articleId;

    @Excel(name = "文章标题")
    private String title;

    @Excel(name = "封面图片")
    private String image;

    @Excel(name = "所属栏目")
    @TableField(exist = false)
    private String categoryName;

    @Excel(name = "栏目ID")
    private String categoryId;

    @Excel(name = "父级栏目名称")
    private String parentName;

    @Excel(name = "父级栏目ID")
    private Integer parentId;

    @Excel(name = "文章内容")
    private String content;

    @Excel(name = "摘要")
    private String comments;

    @Excel(name = "文章来源")
    private String source;

    @Excel(name = "实际阅读量")
    private String actualViews;

    @Excel(name = "作者")
    private String author;

    @Excel(name = "发布时间")
    private Date createTime;

    @Excel(name = "类型")
    private Integer type;

    @Excel(name = "模型")
    private String model;

    @Excel(name = "详情页模板")
    private String detail;

    @Excel(name = "话题")
    private String topic;

    @Excel(name = "关键词")
    private String tags;

    @Excel(name = "产品概述")
    private String overview;

    @Excel(name = "显示方式")
    private Integer showType;

    @Excel(name = "客户端")
    private String platform;

    @Excel(name = "文件列表")
    private String files;

    @Excel(name = "视频地址")
    private String video;

    @Excel(name = "点赞数")
    private Integer likes;

    @Excel(name = "评论数")
    private Integer commentNumbers;

    @Excel(name = "推荐")
    private Integer recommend;

    @Excel(name = "查看密码")
    private String password;

    @Excel(name = "权限")
    private Integer permission;

    @Excel(name = "用户ID")
    private Integer userId;

    @Excel(name = "商户ID")
    private Long merchantId;

    @Excel(name = "语言")
    private String lang;

    @Excel(name = "排序")
    private Integer sortNumber;

    @Excel(name = "状态")
    private Integer status;

    @Excel(name = "租户ID")
    private Integer tenantId;
}
