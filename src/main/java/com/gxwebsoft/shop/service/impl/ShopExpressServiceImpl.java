package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopExpressMapper;
import com.gxwebsoft.shop.service.ShopExpressService;
import com.gxwebsoft.shop.entity.ShopExpress;
import com.gxwebsoft.shop.param.ShopExpressParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流公司Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Service
public class ShopExpressServiceImpl extends ServiceImpl<ShopExpressMapper, ShopExpress> implements ShopExpressService {

    @Override
    public PageResult<ShopExpress> pageRel(ShopExpressParam param) {
        PageParam<ShopExpress, ShopExpressParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopExpress> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopExpress> listRel(ShopExpressParam param) {
        List<ShopExpress> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopExpress, ShopExpressParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopExpress getByIdRel(Integer expressId) {
        ShopExpressParam param = new ShopExpressParam();
        param.setExpressId(expressId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
