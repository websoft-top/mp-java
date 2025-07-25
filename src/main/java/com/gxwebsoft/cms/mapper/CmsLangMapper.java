package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsLang;
import com.gxwebsoft.cms.param.CmsLangParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 国际化Mapper
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
public interface CmsLangMapper extends BaseMapper<CmsLang> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsLang>
     */
    List<CmsLang> selectPageRel(@Param("page") IPage<CmsLang> page,
                             @Param("param") CmsLangParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsLang> selectListRel(@Param("param") CmsLangParam param);

}
