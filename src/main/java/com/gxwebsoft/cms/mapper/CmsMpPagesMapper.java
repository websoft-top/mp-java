package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsMpPages;
import com.gxwebsoft.cms.param.CmsMpPagesParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序页面Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpPagesMapper extends BaseMapper<CmsMpPages> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsMpPages>
     */
    List<CmsMpPages> selectPageRel(@Param("page") IPage<CmsMpPages> page,
                             @Param("param") CmsMpPagesParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsMpPages> selectListRel(@Param("param") CmsMpPagesParam param);

}
