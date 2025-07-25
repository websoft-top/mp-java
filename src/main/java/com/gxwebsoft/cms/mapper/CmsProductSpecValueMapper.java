package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsProductSpecValue;
import com.gxwebsoft.cms.param.CmsProductSpecValueParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格值Mapper
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductSpecValueMapper extends BaseMapper<CmsProductSpecValue> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsProductSpecValue>
     */
    List<CmsProductSpecValue> selectPageRel(@Param("page") IPage<CmsProductSpecValue> page,
                             @Param("param") CmsProductSpecValueParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsProductSpecValue> selectListRel(@Param("param") CmsProductSpecValueParam param);

}
