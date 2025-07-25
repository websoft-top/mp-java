package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsWebsite;
import com.gxwebsoft.cms.param.CmsWebsiteParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网站信息记录表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
public interface CmsWebsiteMapper extends BaseMapper<CmsWebsite> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsWebsite>
     */
    List<CmsWebsite> selectPageRel(@Param("page") IPage<CmsWebsite> page,
                             @Param("param") CmsWebsiteParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsWebsite> selectListRel(@Param("param") CmsWebsiteParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<CmsWebsite> selectPageRelAll(@Param("page") IPage<CmsWebsite> page,
                                      @Param("param") CmsWebsiteParam param);

    @InterceptorIgnore(tenantLine = "true")
    CmsWebsite getByIdRelAll(@Param("websiteId") Integer id);

    @InterceptorIgnore(tenantLine = "true")
    boolean updateByIdAll(@Param("param") CmsWebsite cmsWebsite);

    @InterceptorIgnore(tenantLine = "true")
    boolean removeByIdAll(@Param("websiteId") Integer id);

    @InterceptorIgnore(tenantLine = "true")
    CmsWebsite getByTenantId(@Param("tenantId") Integer tenantId);
}
