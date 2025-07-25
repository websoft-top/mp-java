package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopBrandMapper;
import com.gxwebsoft.shop.service.ShopBrandService;
import com.gxwebsoft.shop.entity.ShopBrand;
import com.gxwebsoft.shop.param.ShopBrandParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopBrandServiceImpl extends ServiceImpl<ShopBrandMapper, ShopBrand> implements ShopBrandService {

    @Override
    public PageResult<ShopBrand> pageRel(ShopBrandParam param) {
        PageParam<ShopBrand, ShopBrandParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopBrand> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopBrand> listRel(ShopBrandParam param) {
        List<ShopBrand> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopBrand, ShopBrandParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopBrand getByIdRel(Integer brandId) {
        ShopBrandParam param = new ShopBrandParam();
        param.setBrandId(brandId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
