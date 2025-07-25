package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.RoleMenu;
import com.gxwebsoft.common.system.mapper.RoleMenuMapper;
import com.gxwebsoft.common.system.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色菜单Service实现
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:12
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<Menu> listMenuByUserId(Integer userId, Integer menuType) {
        return baseMapper.listMenuByUserId(userId, menuType);
    }

    @Override
    public List<Menu> listMenuByRoleIds(List<Integer> roleIds, Integer menuType) {
        return baseMapper.listMenuByRoleIds(roleIds, menuType);
    }

}
