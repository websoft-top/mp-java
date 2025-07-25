package com.gxwebsoft.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsArticle对象", description = "文章")
public class CmsArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章类型 0常规 1视频")
    private Integer type;

    @ApiModelProperty(value = "文章模型")
    private String model;

    @ApiModelProperty(value = "内容模板页面")
    private String detail;

    @ApiModelProperty(value = "banner")
    @TableField(exist = false)
    private String banner;

    @ApiModelProperty(value = "访问路径")
    @TableField(exist = false)
    private String path;

    @ApiModelProperty(value = "列表显示方式(10小图展示 20大图展示)")
    private Integer showType;

    @ApiModelProperty(value = "话题")
    private String topic;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "文章分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "当前分类")
    @TableField(exist = false)
    private String categoryName;

    @ApiModelProperty(value = "父级分类ID")
    private Integer parentId;

    @ApiModelProperty(value = "父级分类")
    @TableField(exist = false)
    private String parentName;

    @ApiModelProperty(value = "封面图")
    private String image;

    @ApiModelProperty(value = "封面图宽度")
    private Integer imageWidth;

    @ApiModelProperty(value = "封面图高度")
    private Integer imageHeight;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "到期时间")
    private Date endTime;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "产品概述")
    private String overview;

    @ApiModelProperty(value = "虚拟阅读量(仅用作展示)")
    private Integer virtualViews;

    @ApiModelProperty(value = "实际阅读量")
    private Integer actualViews;

    @ApiModelProperty(value = "评分")
    private BigDecimal rate;

    @ApiModelProperty(value = "可见类型 0所有人 1登录可见 2密码可见")
    private Integer permission;

    @ApiModelProperty(value = "访问密码")
    private String password;

    @ApiModelProperty(value = "验证密码(前端回传)")
    @TableField(exist = false)
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
    private Integer likes;

    @ApiModelProperty(value = "评论数")
    private Integer commentNumbers;

    @ApiModelProperty(value = "提醒谁看")
    private String toUsers;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "国际化语言")
    private String lang;

    @ApiModelProperty(value = "关联默认语言的文章ID,用于同步翻译更新")
    private Integer langArticleId;

    @ApiModelProperty(value = "是否启用自动翻译")
    private Boolean translation;

    @ApiModelProperty(value = "编辑器类型 1 富文本编辑器 2 Markdown编辑器")
    private Integer editor;

    @ApiModelProperty(value = "PDF地址")
    private String pdfUrl;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "商户名称")
    @TableField(exist = false)
    private String merchantName;

    @ApiModelProperty(value = "商户名称")
    @TableField(exist = false)
    private String merchantAvatar;

    @ApiModelProperty(value = "昵称")
    @TableField(exist = false)
    private String nickname;

    @ApiModelProperty(value = "头像")
    @TableField(exist = false)
    private String avatar;

    @ApiModelProperty(value = "是否推荐")
    private Integer recommend;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0已发布, 1待审核 2已驳回 3违规内容")
    private Integer status;

    @ApiModelProperty(value = "状态文本")
    @TableField(exist = false)
    private String statusText;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否更新")
    @TableField(exist = false)
    private Boolean isUpdate;

    @ApiModelProperty(value = "文章内容")
    @TableField(exist = false)
    private String content;

    @ApiModelProperty(value = "已报名人数")
    private Integer bmUsers;

    public String getStatusText() {
      if (this.status == 0) {
        return "已发布";
      }
      if (this.status == 1) {
        return "待审核";
      }
      if (this.status == 2) {
        return "已驳回";
      }
      if (this.status == 3) {
        return "违规内容";
      }
      return "";
    }
}
