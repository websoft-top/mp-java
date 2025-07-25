package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsProduct;
import com.gxwebsoft.cms.param.CmsProductParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品Mapper
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductMapper extends BaseMapper<CmsProduct> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsProduct>
     */
    List<CmsProduct> selectPageRel(@Param("page") IPage<CmsProduct> page,
                             @Param("param") CmsProductParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsProduct> selectListRel(@Param("param") CmsProductParam param);

}
