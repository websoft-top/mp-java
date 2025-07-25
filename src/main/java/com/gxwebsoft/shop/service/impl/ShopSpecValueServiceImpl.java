package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopSpecValueMapper;
import com.gxwebsoft.shop.service.ShopSpecValueService;
import com.gxwebsoft.shop.entity.ShopSpecValue;
import com.gxwebsoft.shop.param.ShopSpecValueParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格值Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
@Service
public class ShopSpecValueServiceImpl extends ServiceImpl<ShopSpecValueMapper, ShopSpecValue> implements ShopSpecValueService {

    @Override
    public PageResult<ShopSpecValue> pageRel(ShopSpecValueParam param) {
        PageParam<ShopSpecValue, ShopSpecValueParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopSpecValue> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopSpecValue> listRel(ShopSpecValueParam param) {
        List<ShopSpecValue> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopSpecValue, ShopSpecValueParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopSpecValue getByIdRel(Integer specValueId) {
        ShopSpecValueParam param = new ShopSpecValueParam();
        param.setSpecValueId(specValueId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
