package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopOrderInfoMapper;
import com.gxwebsoft.shop.service.ShopOrderInfoService;
import com.gxwebsoft.shop.entity.ShopOrderInfo;
import com.gxwebsoft.shop.param.ShopOrderInfoParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 场地Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopOrderInfoServiceImpl extends ServiceImpl<ShopOrderInfoMapper, ShopOrderInfo> implements ShopOrderInfoService {

    @Override
    public PageResult<ShopOrderInfo> pageRel(ShopOrderInfoParam param) {
        PageParam<ShopOrderInfo, ShopOrderInfoParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopOrderInfo> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopOrderInfo> listRel(ShopOrderInfoParam param) {
        List<ShopOrderInfo> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopOrderInfo, ShopOrderInfoParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopOrderInfo getByIdRel(Integer id) {
        ShopOrderInfoParam param = new ShopOrderInfoParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
