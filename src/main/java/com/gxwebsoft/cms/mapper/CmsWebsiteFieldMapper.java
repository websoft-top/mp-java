package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsWebsiteField;
import com.gxwebsoft.cms.param.CmsWebsiteFieldParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用参数Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
public interface CmsWebsiteFieldMapper extends BaseMapper<CmsWebsiteField> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsWebsiteField>
     */
    List<CmsWebsiteField> selectPageRel(@Param("page") IPage<CmsWebsiteField> page,
                             @Param("param") CmsWebsiteFieldParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsWebsiteField> selectListRel(@Param("param") CmsWebsiteFieldParam param);


    @InterceptorIgnore(tenantLine = "true")
    List<CmsWebsiteField> selectListAllRel(@Param("param") CmsWebsiteFieldParam param);

}
