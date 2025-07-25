package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsMpMenu;
import com.gxwebsoft.cms.param.CmsMpMenuParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序端菜单Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpMenuMapper extends BaseMapper<CmsMpMenu> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsMpMenu>
     */
    List<CmsMpMenu> selectPageRel(@Param("page") IPage<CmsMpMenu> page,
                             @Param("param") CmsMpMenuParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsMpMenu> selectListRel(@Param("param") CmsMpMenuParam param);

}
