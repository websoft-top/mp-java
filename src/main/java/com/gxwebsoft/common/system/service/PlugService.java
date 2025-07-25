package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Plug;
import com.gxwebsoft.common.system.param.PlugParam;

import java.util.List;

/**
 * 插件扩展Service
 *
 * @author 科技小王子
 * @since 2023-05-18 11:57:37
 */
public interface PlugService extends IService<Plug> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<Plug>
     */
    PageResult<Plug> pageRel(PlugParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<Plug>
     */
    List<Plug> listRel(PlugParam param);

    /**
     * 根据id查询
     *
     * @param menuId 菜单id
     * @return Plug
     */
    Plug getByIdRel(Integer menuId);

    Boolean cloneMenu(PlugParam param);

}
