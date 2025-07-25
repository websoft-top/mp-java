package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsDomain;
import com.gxwebsoft.cms.param.CmsDomainParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网站域名记录表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
public interface CmsDomainMapper extends BaseMapper<CmsDomain> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsDomain>
     */
    List<CmsDomain> selectPageRel(@Param("page") IPage<CmsDomain> page,
                             @Param("param") CmsDomainParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsDomain> selectListRel(@Param("param") CmsDomainParam param);

    @InterceptorIgnore(tenantLine = "true")
    CmsDomain getDomain(@Param("domain") String domain);
}
