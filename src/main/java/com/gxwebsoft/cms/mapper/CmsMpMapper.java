package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsMp;
import com.gxwebsoft.cms.param.CmsMpParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序信息Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpMapper extends BaseMapper<CmsMp> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsMp>
     */
    List<CmsMp> selectPageRel(@Param("page") IPage<CmsMp> page,
                             @Param("param") CmsMpParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsMp> selectListRel(@Param("param") CmsMpParam param);

}
