package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.system.entity.Role;
import com.gxwebsoft.common.system.mapper.RoleMapper;
import com.gxwebsoft.common.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现类
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
