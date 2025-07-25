package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.UserCollection;
import com.gxwebsoft.common.system.mapper.UserCollectionMapper;
import com.gxwebsoft.common.system.param.UserCollectionParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的收藏Service实现
 *
 * @author 科技小王子
 * @since 2024-04-28 18:08:32
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Override
    public PageResult<UserCollection> pageRel(UserCollectionParam param) {
        PageParam<UserCollection, UserCollectionParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<UserCollection> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<UserCollection> listRel(UserCollectionParam param) {
        List<UserCollection> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<UserCollection, UserCollectionParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public UserCollection getByIdRel(Integer id) {
        UserCollectionParam param = new UserCollectionParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
