package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopUsersMapper;
import com.gxwebsoft.shop.service.ShopUsersService;
import com.gxwebsoft.shop.entity.ShopUsers;
import com.gxwebsoft.shop.param.ShopUsersParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Service
public class ShopUsersServiceImpl extends ServiceImpl<ShopUsersMapper, ShopUsers> implements ShopUsersService {

    @Override
    public PageResult<ShopUsers> pageRel(ShopUsersParam param) {
        PageParam<ShopUsers, ShopUsersParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopUsers> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopUsers> listRel(ShopUsersParam param) {
        List<ShopUsers> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopUsers, ShopUsersParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopUsers getByIdRel(Integer id) {
        ShopUsersParam param = new ShopUsersParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
