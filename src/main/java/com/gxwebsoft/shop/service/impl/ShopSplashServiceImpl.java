package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopSplashMapper;
import com.gxwebsoft.shop.service.ShopSplashService;
import com.gxwebsoft.shop.entity.ShopSplash;
import com.gxwebsoft.shop.param.ShopSplashParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开屏广告Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Service
public class ShopSplashServiceImpl extends ServiceImpl<ShopSplashMapper, ShopSplash> implements ShopSplashService {

    @Override
    public PageResult<ShopSplash> pageRel(ShopSplashParam param) {
        PageParam<ShopSplash, ShopSplashParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopSplash> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopSplash> listRel(ShopSplashParam param) {
        List<ShopSplash> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopSplash, ShopSplashParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopSplash getByIdRel(Integer id) {
        ShopSplashParam param = new ShopSplashParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
