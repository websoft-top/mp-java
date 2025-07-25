package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopMerchantApplyMapper;
import com.gxwebsoft.shop.service.ShopMerchantApplyService;
import com.gxwebsoft.shop.entity.ShopMerchantApply;
import com.gxwebsoft.shop.param.ShopMerchantApplyParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户入驻申请Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopMerchantApplyServiceImpl extends ServiceImpl<ShopMerchantApplyMapper, ShopMerchantApply> implements ShopMerchantApplyService {

    @Override
    public PageResult<ShopMerchantApply> pageRel(ShopMerchantApplyParam param) {
        PageParam<ShopMerchantApply, ShopMerchantApplyParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopMerchantApply> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopMerchantApply> listRel(ShopMerchantApplyParam param) {
        List<ShopMerchantApply> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopMerchantApply, ShopMerchantApplyParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopMerchantApply getByIdRel(Integer applyId) {
        ShopMerchantApplyParam param = new ShopMerchantApplyParam();
        param.setApplyId(applyId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
