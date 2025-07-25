package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.UserReferee;
import com.gxwebsoft.common.system.mapper.UserRefereeMapper;
import com.gxwebsoft.common.system.param.UserRefereeParam;
import com.gxwebsoft.common.system.service.UserRefereeService;
import com.gxwebsoft.common.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户推荐关系表Service实现
 *
 * @author 科技小王子
 * @since 2023-10-07 22:56:36
 */
@Service
public class UserRefereeServiceImpl extends ServiceImpl<UserRefereeMapper, UserReferee> implements UserRefereeService {

    @Resource
    private UserService userService;

    @Override
    public PageResult<UserReferee> pageRel(UserRefereeParam param) {
        PageParam<UserReferee, UserRefereeParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<UserReferee> list = baseMapper.selectPageRel(page, param);
        for (UserReferee userReferee : list) {
            userReferee.setUser(userService.getById(userReferee.getUserId()));
        }
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<UserReferee> listRel(UserRefereeParam param) {
        List<UserReferee> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<UserReferee, UserRefereeParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public UserReferee getByIdRel(Integer id) {
        UserRefereeParam param = new UserRefereeParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public UserReferee check(Integer dealerId, Integer userId) {
        return getOne(
                new LambdaQueryWrapper<UserReferee>()
                        .eq(UserReferee::getDealerId, dealerId)
                        .eq(UserReferee::getUserId, userId)
        );
    }

    @Override
    public UserReferee getByUserId(Integer userId) {
        return getOne(
                new LambdaQueryWrapper<UserReferee>()
                        .eq(UserReferee::getUserId, userId)
                        .last("limit 1")
        );
    }

}
