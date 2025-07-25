package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopOrderInfoLogMapper;
import com.gxwebsoft.shop.service.ShopOrderInfoLogService;
import com.gxwebsoft.shop.entity.ShopOrderInfoLog;
import com.gxwebsoft.shop.param.ShopOrderInfoLogParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单核销Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopOrderInfoLogServiceImpl extends ServiceImpl<ShopOrderInfoLogMapper, ShopOrderInfoLog> implements ShopOrderInfoLogService {

    @Override
    public PageResult<ShopOrderInfoLog> pageRel(ShopOrderInfoLogParam param) {
        PageParam<ShopOrderInfoLog, ShopOrderInfoLogParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopOrderInfoLog> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopOrderInfoLog> listRel(ShopOrderInfoLogParam param) {
        List<ShopOrderInfoLog> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopOrderInfoLog, ShopOrderInfoLogParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopOrderInfoLog getByIdRel(Integer id) {
        ShopOrderInfoLogParam param = new ShopOrderInfoLogParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
