package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsMpMenu;
import com.gxwebsoft.cms.param.CmsMpMenuParam;

import java.util.List;

/**
 * 小程序端菜单Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpMenuService extends IService<CmsMpMenu> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsMpMenu>
     */
    PageResult<CmsMpMenu> pageRel(CmsMpMenuParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsMpMenu>
     */
    List<CmsMpMenu> listRel(CmsMpMenuParam param);

    /**
     * 根据id查询
     *
     * @param menuId ID
     * @return CmsMpMenu
     */
    CmsMpMenu getByIdRel(Integer menuId);

}
