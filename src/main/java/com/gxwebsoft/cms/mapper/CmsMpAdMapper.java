package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsMpAd;
import com.gxwebsoft.cms.param.CmsMpAdParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序广告位Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpAdMapper extends BaseMapper<CmsMpAd> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsMpAd>
     */
    List<CmsMpAd> selectPageRel(@Param("page") IPage<CmsMpAd> page,
                             @Param("param") CmsMpAdParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsMpAd> selectListRel(@Param("param") CmsMpAdParam param);

}
