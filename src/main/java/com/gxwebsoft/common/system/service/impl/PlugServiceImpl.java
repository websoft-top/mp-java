package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Plug;
import com.gxwebsoft.common.system.mapper.PlugMapper;
import com.gxwebsoft.common.system.param.PlugParam;
import com.gxwebsoft.common.system.service.PlugService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 插件扩展Service实现
 *
 * @author 科技小王子
 * @since 2023-05-18 11:57:37
 */
@Service
public class PlugServiceImpl extends ServiceImpl<PlugMapper, Plug> implements PlugService {

    @Override
    public PageResult<Plug> pageRel(PlugParam param) {
        PageParam<Plug, PlugParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<Plug> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<Plug> listRel(PlugParam param) {
        List<Plug> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<Plug, PlugParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public Plug getByIdRel(Integer menuId) {
        PlugParam param = new PlugParam();
        param.setMenuId(menuId);
        return param.getOne(baseMapper.selectListRel(param));
    }

  @Override
  @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
  public Boolean cloneMenu(PlugParam param) {
//    System.out.println("准备待克隆的菜单数据 = " + param);
    // 删除本项目菜单
    baseMapper.delete(new LambdaQueryWrapper<Plug>().eq(Plug::getDeleted,0));
    // 顶级栏目
    param.setParentId(0);
    final List<Plug> list = baseMapper.getMenuByClone(param);
//      final List<Integer> menuIds = list.stream().map(Menu::getMenuId).collect(Collectors.toList());

    list.forEach(d -> {
      Plug plug = new Plug();
      plug.setParentId(0);
      plug.setTitle(d.getTitle());
      plug.setPath(d.getPath());
      plug.setComponent(d.getComponent());
      plug.setMenuType(d.getMenuType());
      plug.setSortNumber(d.getSortNumber());
      plug.setAuthority(d.getAuthority());
      plug.setIcon(d.getIcon());
      plug.setHide(d.getHide());
      plug.setMeta(d.getMeta());
      save(plug);
      // 二级菜单
      param.setParentId(d.getMenuId());
      final List<Plug> list1 = baseMapper.getMenuByClone(param);
      list1.forEach(d1 -> {
        final Plug menu1 = new Plug();
        menu1.setParentId(plug.getMenuId());
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
        final List<Plug> list2 = baseMapper.getMenuByClone(param);
        list2.forEach(d2 -> {
          final Plug menu2 = new Plug();
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
    return true;
  }

}
