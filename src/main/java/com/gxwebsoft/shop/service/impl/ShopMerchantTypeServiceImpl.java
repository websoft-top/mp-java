package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopMerchantTypeMapper;
import com.gxwebsoft.shop.service.ShopMerchantTypeService;
import com.gxwebsoft.shop.entity.ShopMerchantType;
import com.gxwebsoft.shop.param.ShopMerchantTypeParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户类型Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopMerchantTypeServiceImpl extends ServiceImpl<ShopMerchantTypeMapper, ShopMerchantType> implements ShopMerchantTypeService {

    @Override
    public PageResult<ShopMerchantType> pageRel(ShopMerchantTypeParam param) {
        PageParam<ShopMerchantType, ShopMerchantTypeParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopMerchantType> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopMerchantType> listRel(ShopMerchantTypeParam param) {
        List<ShopMerchantType> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopMerchantType, ShopMerchantTypeParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopMerchantType getByIdRel(Integer id) {
        ShopMerchantTypeParam param = new ShopMerchantTypeParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
