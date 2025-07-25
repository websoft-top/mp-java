package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.system.entity.Role;
import com.gxwebsoft.common.system.entity.UserRole;
import com.gxwebsoft.common.system.mapper.UserRoleMapper;
import com.gxwebsoft.common.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色Service实现
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:36
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


    @Override
    public int saveBatch(Integer userId, List<Integer> roleIds) {
        return baseMapper.insertBatch(userId, roleIds);
    }

    @Override
    public List<Role> listByUserId(Integer userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<Role> listByUserIds(List<Integer> userIds) {
        return baseMapper.selectByUserIds(userIds);
    }

}
