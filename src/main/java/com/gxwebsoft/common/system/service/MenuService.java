package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.param.MenuParam;

/**
 * 菜单Service
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:31
 */
public interface MenuService extends IService<Menu> {

    Boolean cloneMenu(MenuParam param);

    Boolean install(Integer id);
}
