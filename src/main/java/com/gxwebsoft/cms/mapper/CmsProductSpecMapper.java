package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsProductSpec;
import com.gxwebsoft.cms.param.CmsProductSpecParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格Mapper
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductSpecMapper extends BaseMapper<CmsProductSpec> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsProductSpec>
     */
    List<CmsProductSpec> selectPageRel(@Param("page") IPage<CmsProductSpec> page,
                             @Param("param") CmsProductSpecParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsProductSpec> selectListRel(@Param("param") CmsProductSpecParam param);

}
