package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopDealerCapitalMapper;
import com.gxwebsoft.shop.service.ShopDealerCapitalService;
import com.gxwebsoft.shop.entity.ShopDealerCapital;
import com.gxwebsoft.shop.param.ShopDealerCapitalParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分销商资金明细表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopDealerCapitalServiceImpl extends ServiceImpl<ShopDealerCapitalMapper, ShopDealerCapital> implements ShopDealerCapitalService {

    @Override
    public PageResult<ShopDealerCapital> pageRel(ShopDealerCapitalParam param) {
        PageParam<ShopDealerCapital, ShopDealerCapitalParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopDealerCapital> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopDealerCapital> listRel(ShopDealerCapitalParam param) {
        List<ShopDealerCapital> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopDealerCapital, ShopDealerCapitalParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopDealerCapital getByIdRel(Integer id) {
        ShopDealerCapitalParam param = new ShopDealerCapitalParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
