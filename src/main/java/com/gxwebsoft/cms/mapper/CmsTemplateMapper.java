package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsTemplate;
import com.gxwebsoft.cms.param.CmsTemplateParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网站模版Mapper
 *
 * @author 科技小王子
 * @since 2025-01-21 14:21:16
 */
public interface CmsTemplateMapper extends BaseMapper<CmsTemplate> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsTemplate>
     */
    List<CmsTemplate> selectPageRel(@Param("page") IPage<CmsTemplate> page,
                             @Param("param") CmsTemplateParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsTemplate> selectListRel(@Param("param") CmsTemplateParam param);

}
