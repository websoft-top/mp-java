package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsLangLog;
import com.gxwebsoft.cms.param.CmsLangLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 国际化记录启用Mapper
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
public interface CmsLangLogMapper extends BaseMapper<CmsLangLog> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsLangLog>
     */
    List<CmsLangLog> selectPageRel(@Param("page") IPage<CmsLangLog> page,
                             @Param("param") CmsLangLogParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsLangLog> selectListRel(@Param("param") CmsLangLogParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<CmsLangLog> selectListAllRel(@Param("param") CmsLangLogParam param);

}
