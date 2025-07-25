package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsDesign;
import com.gxwebsoft.cms.param.CmsDesignParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 页面管理记录表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsDesignMapper extends BaseMapper<CmsDesign> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsDesign>
     */
    List<CmsDesign> selectPageRel(@Param("page") IPage<CmsDesign> page,
                             @Param("param") CmsDesignParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsDesign> selectListRel(@Param("param") CmsDesignParam param);

}
