package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单Mapper
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:21
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 查询用户的菜单
     *
     * @param userId   用户id
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Menu> listMenuByUserId(@Param("userId") Integer userId, @Param("menuType") Integer menuType);

    /**
     * 根据角色id查询菜单
     *
     * @param roleIds  角色id
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Menu> listMenuByRoleIds(@Param("roleIds") List<Integer> roleIds, @Param("menuType") Integer menuType);

}
