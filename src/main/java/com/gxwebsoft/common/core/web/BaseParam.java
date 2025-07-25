package com.gxwebsoft.common.core.web;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.utils.CommonUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询参数基本字段
 *
 * @author WebSoft
 * @since 2021-08-26 22:14:43
 */
@Data
public class BaseParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    @ApiModelProperty("分页查询页码")
    private Long page;

    @TableField(exist = false)
    @ApiModelProperty("分页查询每页数量")
    private Long limit;

    @TableField(exist = false)
    @ApiModelProperty("国际化")
    private String lang;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序字段", notes = "排序字段或sql, 如果是sql则order字段无用, 如: `id asc, name desc`")
    private String sort;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序方式", notes = "sort是字段名称时对应的排序方式, asc升序, desc降序")
    private String order;

    @QueryField(value = "create_time", type = QueryType.GE)
    @TableField(exist = false)
    @ApiModelProperty("创建时间起始值")
    private String createTimeStart;

    @QueryField(value = "create_time", type = QueryType.LE)
    @TableField(exist = false)
    @ApiModelProperty("创建时间结束值")
    private String createTimeEnd;

    @QueryField(value = "create_time", type = QueryType.GE)
    @ApiModelProperty("搜索场景")
    @TableField(exist = false)
    private String sceneType;

    @ApiModelProperty("模糊搜素")
    @TableField(exist = false)
    private String keywords;

    @ApiModelProperty("token")
    @TableField(exist = false)
    private String token;

    @ApiModelProperty("租户ID")
    @TableField(exist = false)
    private Integer tenantId;

    @ApiModelProperty(value = "商户ID")
    @TableField(exist = false)
    private Long merchantId;

    /**
     * 获取集合中的第一条数据
     *
     * @param records 集合
     * @return 第一条数据
     */
    public <T> T getOne(List<T> records) {
        return CommonUtil.listGetOne(records);
    }

    /**
     * 国际化参数
     */
    public String getLang(){
      if(StrUtil.isBlank(this.lang)){
        return null;
      }
      if(this.lang.equals("zh")){
        return "zh_CN";
      }
      return this.lang;
    }

}
