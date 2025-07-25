package com.gxwebsoft.cms.entity;

import cn.hutool.core.util.DesensitizedUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gxwebsoft.common.core.utils.JSONUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 广告位
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CmsAd对象", description = "广告位")
public class CmsAd implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ad_id", type = IdType.AUTO)
    private Integer adId;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "栏目ID")
    private Integer categoryId;

    @ApiModelProperty(value = "栏目名称")
    @TableField(exist = false)
    private String categoryName;

    @ApiModelProperty(value = "广告位名称")
    private String name;

    @ApiModelProperty(value = "宽")
    private String width;

    @ApiModelProperty(value = "高")
    private String height;

    @ApiModelProperty(value = "边框")
    private String border;

    @ApiModelProperty(value = "CSS样式")
    private String style;

    @ApiModelProperty(value = "广告图片")
    private String images;

    @ApiModelProperty(value = "广告图片")
    @TableField(exist = false)
    private JSONArray imageList;

    @ApiModelProperty(value = "路由/链接地址")
    private String path;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "国际化语言")
    private String lang;

    @ApiModelProperty(value = "排序(数字越小越靠前)")
    private Integer sortNumber;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public JSONArray getImageList() {
      return JSON.parseArray(this.images);
    }
}
