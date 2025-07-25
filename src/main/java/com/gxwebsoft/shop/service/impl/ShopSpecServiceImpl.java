package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopSpecMapper;
import com.gxwebsoft.shop.service.ShopSpecService;
import com.gxwebsoft.shop.entity.ShopSpec;
import com.gxwebsoft.shop.param.ShopSpecParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
@Service
public class ShopSpecServiceImpl extends ServiceImpl<ShopSpecMapper, ShopSpec> implements ShopSpecService {

    @Override
    public PageResult<ShopSpec> pageRel(ShopSpecParam param) {
        PageParam<ShopSpec, ShopSpecParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopSpec> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopSpec> listRel(ShopSpecParam param) {
        List<ShopSpec> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopSpec, ShopSpecParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopSpec getByIdRel(Integer specId) {
        ShopSpecParam param = new ShopSpecParam();
        param.setSpecId(specId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
