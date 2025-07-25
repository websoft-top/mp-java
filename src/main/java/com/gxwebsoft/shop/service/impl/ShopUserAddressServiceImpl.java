package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopUserAddressMapper;
import com.gxwebsoft.shop.service.ShopUserAddressService;
import com.gxwebsoft.shop.entity.ShopUserAddress;
import com.gxwebsoft.shop.param.ShopUserAddressParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址Service实现
 *
 * @author 科技小王子
 * @since 2025-07-22 23:06:40
 */
@Service
public class ShopUserAddressServiceImpl extends ServiceImpl<ShopUserAddressMapper, ShopUserAddress> implements ShopUserAddressService {

    @Override
    public PageResult<ShopUserAddress> pageRel(ShopUserAddressParam param) {
        PageParam<ShopUserAddress, ShopUserAddressParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopUserAddress> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopUserAddress> listRel(ShopUserAddressParam param) {
        List<ShopUserAddress> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopUserAddress, ShopUserAddressParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopUserAddress getByIdRel(Integer id) {
        ShopUserAddressParam param = new ShopUserAddressParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
