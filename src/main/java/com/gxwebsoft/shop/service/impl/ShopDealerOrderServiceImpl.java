package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopDealerOrderMapper;
import com.gxwebsoft.shop.service.ShopDealerOrderService;
import com.gxwebsoft.shop.entity.ShopDealerOrder;
import com.gxwebsoft.shop.param.ShopDealerOrderParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分销商订单记录表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopDealerOrderServiceImpl extends ServiceImpl<ShopDealerOrderMapper, ShopDealerOrder> implements ShopDealerOrderService {

    @Override
    public PageResult<ShopDealerOrder> pageRel(ShopDealerOrderParam param) {
        PageParam<ShopDealerOrder, ShopDealerOrderParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopDealerOrder> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopDealerOrder> listRel(ShopDealerOrderParam param) {
        List<ShopDealerOrder> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopDealerOrder, ShopDealerOrderParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopDealerOrder getByIdRel(Integer id) {
        ShopDealerOrderParam param = new ShopDealerOrderParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
