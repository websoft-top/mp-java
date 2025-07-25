package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopMerchantMapper;
import com.gxwebsoft.shop.service.ShopMerchantService;
import com.gxwebsoft.shop.entity.ShopMerchant;
import com.gxwebsoft.shop.param.ShopMerchantParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopMerchantServiceImpl extends ServiceImpl<ShopMerchantMapper, ShopMerchant> implements ShopMerchantService {

    @Override
    public PageResult<ShopMerchant> pageRel(ShopMerchantParam param) {
        PageParam<ShopMerchant, ShopMerchantParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopMerchant> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopMerchant> listRel(ShopMerchantParam param) {
        List<ShopMerchant> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopMerchant, ShopMerchantParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopMerchant getByIdRel(Long merchantId) {
        ShopMerchantParam param = new ShopMerchantParam();
        param.setMerchantId(merchantId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
