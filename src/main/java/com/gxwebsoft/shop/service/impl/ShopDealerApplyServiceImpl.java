package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopDealerApplyMapper;
import com.gxwebsoft.shop.service.ShopDealerApplyService;
import com.gxwebsoft.shop.entity.ShopDealerApply;
import com.gxwebsoft.shop.param.ShopDealerApplyParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分销商申请记录表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopDealerApplyServiceImpl extends ServiceImpl<ShopDealerApplyMapper, ShopDealerApply> implements ShopDealerApplyService {

    @Override
    public PageResult<ShopDealerApply> pageRel(ShopDealerApplyParam param) {
        PageParam<ShopDealerApply, ShopDealerApplyParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopDealerApply> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopDealerApply> listRel(ShopDealerApplyParam param) {
        List<ShopDealerApply> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopDealerApply, ShopDealerApplyParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopDealerApply getByIdRel(Integer applyId) {
        ShopDealerApplyParam param = new ShopDealerApplyParam();
        param.setApplyId(applyId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
