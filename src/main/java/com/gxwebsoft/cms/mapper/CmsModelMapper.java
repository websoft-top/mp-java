package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.param.CmsModelParam;
import com.gxwebsoft.cms.param.CmsWebsiteFieldParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模型Mapper
 *
 * @author 科技小王子
 * @since 2024-11-26 15:44:53
 */
public interface CmsModelMapper extends BaseMapper<CmsModel> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsModel>
     */
    List<CmsModel> selectPageRel(@Param("page") IPage<CmsModel> page,
                             @Param("param") CmsModelParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsModel> selectListRel(@Param("param") CmsModelParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<CmsModel> selectListAllRel(@Param("param") CmsModelParam param);
}
