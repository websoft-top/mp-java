package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsComponents;
import com.gxwebsoft.cms.param.CmsComponentsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组件Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsComponentsMapper extends BaseMapper<CmsComponents> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsComponents>
     */
    List<CmsComponents> selectPageRel(@Param("page") IPage<CmsComponents> page,
                             @Param("param") CmsComponentsParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsComponents> selectListRel(@Param("param") CmsComponentsParam param);

}
