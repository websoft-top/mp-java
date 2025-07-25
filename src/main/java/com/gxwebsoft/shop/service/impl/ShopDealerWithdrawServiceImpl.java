package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopDealerWithdrawMapper;
import com.gxwebsoft.shop.service.ShopDealerWithdrawService;
import com.gxwebsoft.shop.entity.ShopDealerWithdraw;
import com.gxwebsoft.shop.param.ShopDealerWithdrawParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分销商提现明细表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopDealerWithdrawServiceImpl extends ServiceImpl<ShopDealerWithdrawMapper, ShopDealerWithdraw> implements ShopDealerWithdrawService {

    @Override
    public PageResult<ShopDealerWithdraw> pageRel(ShopDealerWithdrawParam param) {
        PageParam<ShopDealerWithdraw, ShopDealerWithdrawParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopDealerWithdraw> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopDealerWithdraw> listRel(ShopDealerWithdrawParam param) {
        List<ShopDealerWithdraw> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopDealerWithdraw, ShopDealerWithdrawParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopDealerWithdraw getByIdRel(Integer id) {
        ShopDealerWithdrawParam param = new ShopDealerWithdrawParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
