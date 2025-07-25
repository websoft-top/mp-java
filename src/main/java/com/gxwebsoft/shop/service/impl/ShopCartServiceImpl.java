package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopCartMapper;
import com.gxwebsoft.shop.service.ShopCartService;
import com.gxwebsoft.shop.entity.ShopCart;
import com.gxwebsoft.shop.param.ShopCartParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCart> implements ShopCartService {

    @Override
    public PageResult<ShopCart> pageRel(ShopCartParam param) {
        PageParam<ShopCart, ShopCartParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopCart> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopCart> listRel(ShopCartParam param) {
        List<ShopCart> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopCart, ShopCartParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopCart getByIdRel(Long id) {
        ShopCartParam param = new ShopCartParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
