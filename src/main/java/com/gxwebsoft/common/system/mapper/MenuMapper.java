package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.param.MenuParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单Mapper
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:32
 */
public interface MenuMapper extends BaseMapper<Menu> {
  @InterceptorIgnore(tenantLine = "true")
  List<Menu> getMenuByClone(@Param("param") MenuParam param);

}
