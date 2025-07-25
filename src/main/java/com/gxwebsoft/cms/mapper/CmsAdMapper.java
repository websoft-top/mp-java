package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsAd;
import com.gxwebsoft.cms.entity.CmsLangLog;
import com.gxwebsoft.cms.param.CmsAdParam;
import com.gxwebsoft.cms.param.CmsLangLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广告位Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsAdMapper extends BaseMapper<CmsAd> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsAd>
     */
    List<CmsAd> selectPageRel(@Param("page") IPage<CmsAd> page,
                             @Param("param") CmsAdParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsAd> selectListRel(@Param("param") CmsAdParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<CmsAd> selectListAllRel(@Param("param") CmsAdParam param);
}
