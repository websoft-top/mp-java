package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsForm;
import com.gxwebsoft.cms.param.CmsFormParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表单设计表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsFormMapper extends BaseMapper<CmsForm> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsForm>
     */
    List<CmsForm> selectPageRel(@Param("page") IPage<CmsForm> page,
                             @Param("param") CmsFormParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsForm> selectListRel(@Param("param") CmsFormParam param);

}
