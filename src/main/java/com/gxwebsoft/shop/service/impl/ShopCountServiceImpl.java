package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopCountMapper;
import com.gxwebsoft.shop.service.ShopCountService;
import com.gxwebsoft.shop.entity.ShopCount;
import com.gxwebsoft.shop.param.ShopCountParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商城销售统计表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopCountServiceImpl extends ServiceImpl<ShopCountMapper, ShopCount> implements ShopCountService {

    @Override
    public PageResult<ShopCount> pageRel(ShopCountParam param) {
        PageParam<ShopCount, ShopCountParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopCount> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopCount> listRel(ShopCountParam param) {
        List<ShopCount> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopCount, ShopCountParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopCount getByIdRel(Integer id) {
        ShopCountParam param = new ShopCountParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
