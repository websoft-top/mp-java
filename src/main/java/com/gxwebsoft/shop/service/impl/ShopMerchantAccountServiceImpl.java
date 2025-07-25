package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopMerchantAccountMapper;
import com.gxwebsoft.shop.service.ShopMerchantAccountService;
import com.gxwebsoft.shop.entity.ShopMerchantAccount;
import com.gxwebsoft.shop.param.ShopMerchantAccountParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户账号Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopMerchantAccountServiceImpl extends ServiceImpl<ShopMerchantAccountMapper, ShopMerchantAccount> implements ShopMerchantAccountService {

    @Override
    public PageResult<ShopMerchantAccount> pageRel(ShopMerchantAccountParam param) {
        PageParam<ShopMerchantAccount, ShopMerchantAccountParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopMerchantAccount> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopMerchantAccount> listRel(ShopMerchantAccountParam param) {
        List<ShopMerchantAccount> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopMerchantAccount, ShopMerchantAccountParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopMerchantAccount getByIdRel(Integer id) {
        ShopMerchantAccountParam param = new ShopMerchantAccountParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
