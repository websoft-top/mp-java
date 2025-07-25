package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.Role;
import com.gxwebsoft.common.system.entity.RoleMenu;
import com.gxwebsoft.common.system.mapper.MenuMapper;
import com.gxwebsoft.common.system.param.MenuParam;
import com.gxwebsoft.common.system.service.MenuService;
import com.gxwebsoft.common.system.service.RoleMenuService;
import com.gxwebsoft.common.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单Service实现
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private Integer plugMenuId;
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
    public Boolean cloneMenu(MenuParam param) {
//      System.out.println("准备待克隆的菜单数据 = " + param);
      // 删除本项目菜单
      baseMapper.delete(new LambdaQueryWrapper<Menu>().eq(Menu::getDeleted,0));
      // 顶级栏目
      param.setParentId(0);
//      final List<Menu> list = baseMapper.getMenuByClone(param);
////      final List<Integer> menuIds = list.stream().map(Menu::getMenuId).collect(Collectors.toList());
      doCloneMenu(baseMapper.getMenuByClone(param));
      return true;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
    public Boolean install(Integer id) {
      // 1.插件绑定的菜单ID
      final MenuParam param = new MenuParam();
      param.setMenuId(id);
      final List<Menu> list = baseMapper.getMenuByClone(param);
      // TODO 克隆当前插件到顶级菜单
      doCloneMenu(list);

      // 2.查找当前租户的超管权限的roleId
      final Role superAdmin = roleService.getOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleCode, "superAdmin"));
      final Integer roleId = superAdmin.getRoleId();
      final Integer tenantId = superAdmin.getTenantId();
      // 3.勾选菜单根权限
      final RoleMenu roleMenu0 = new RoleMenu();
      roleMenu0.setRoleId(roleId);
      roleMenu0.setMenuId(this.plugMenuId);
      roleMenuService.save(roleMenu0);

      // 4.勾选根节点下的子菜单权限
      final MenuParam menuParam = new MenuParam();
      menuParam.setParentId(this.plugMenuId);
      menuParam.setTenantId(tenantId);
      final List<Menu> menuList = baseMapper.getMenuByClone(menuParam);
      menuList.forEach(d->{
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(d.getMenuId());
        roleMenuService.save(roleMenu);
      });
      // 5.调整新插件的排序
      final Menu menu = baseMapper.selectById(this.plugMenuId);
      menu.setSortNumber(100);
      baseMapper.updateById(menu);
      return true;
    }

    // 克隆菜单
    private void doCloneMenu(List<Menu> list) {
      final MenuParam param = new MenuParam();
      list.forEach(d -> {
        Menu menu = new Menu();
        menu.setParentId(0);
        menu.setTitle(d.getTitle());
        menu.setPath(d.getPath());
        menu.setComponent(d.getComponent());
        menu.setMenuType(d.getMenuType());
        menu.setSortNumber(d.getSortNumber());
        menu.setAuthority(d.getAuthority());
        menu.setIcon(d.getIcon());
        menu.setHide(d.getHide());
        menu.setMeta(d.getMeta());
        save(menu);
        this.plugMenuId = menu.getMenuId();
        // 二级菜单
        param.setParentId(d.getMenuId());
        final List<Menu> list1 = baseMapper.getMenuByClone(param);
        list1.forEach(d1 -> {
          final Menu menu1 = new Menu();
          menu1.setParentId(menu.getMenuId());
          menu1.setTitle(d1.getTitle());
          menu1.setPath(d1.getPath());
          menu1.setComponent(d1.getComponent());
          menu1.setMenuType(d1.getMenuType());
          menu1.setSortNumber(d1.getSortNumber());
          menu1.setAuthority(d1.getAuthority());
          menu1.setIcon(d1.getIcon());
          menu1.setHide(d1.getHide());
          menu1.setMeta(d1.getMeta());
          save(menu1);
          // 三级菜单
          param.setParentId(d1.getMenuId());
          final List<Menu> list2 = baseMapper.getMenuByClone(param);
          list2.forEach(d2 -> {
            final Menu menu2 = new Menu();
            menu2.setParentId(menu1.getMenuId());
            menu2.setTitle(d2.getTitle());
            menu2.setPath(d2.getPath());
            menu2.setComponent(d2.getComponent());
            menu2.setMenuType(d2.getMenuType());
            menu2.setSortNumber(d2.getSortNumber());
            menu2.setAuthority(d2.getAuthority());
            menu2.setIcon(d2.getIcon());
            menu2.setHide(d2.getHide());
            menu2.setMeta(d2.getMeta());
            save(menu2);
          });
        });
      });
    }
}
