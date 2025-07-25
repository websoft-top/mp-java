package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.param.CmsModelParam;
import com.gxwebsoft.cms.param.CmsNavigationParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网站导航记录表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsNavigationMapper extends BaseMapper<CmsNavigation> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsNavigation>
     */
    List<CmsNavigation> selectPageRel(@Param("page") IPage<CmsNavigation> page,
                             @Param("param") CmsNavigationParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsNavigation> selectListRel(@Param("param") CmsNavigationParam param);


    @InterceptorIgnore(tenantLine = "true")
    List<CmsNavigation> selectListAllRel(@Param("param") CmsNavigationParam param);


}
