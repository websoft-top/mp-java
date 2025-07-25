package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsIncomeConfigMapper;
import com.gxwebsoft.shop.service.ShopGoodsIncomeConfigService;
import com.gxwebsoft.shop.entity.ShopGoodsIncomeConfig;
import com.gxwebsoft.shop.param.ShopGoodsIncomeConfigParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分润配置Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopGoodsIncomeConfigServiceImpl extends ServiceImpl<ShopGoodsIncomeConfigMapper, ShopGoodsIncomeConfig> implements ShopGoodsIncomeConfigService {

    @Override
    public PageResult<ShopGoodsIncomeConfig> pageRel(ShopGoodsIncomeConfigParam param) {
        PageParam<ShopGoodsIncomeConfig, ShopGoodsIncomeConfigParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsIncomeConfig> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsIncomeConfig> listRel(ShopGoodsIncomeConfigParam param) {
        List<ShopGoodsIncomeConfig> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsIncomeConfig, ShopGoodsIncomeConfigParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsIncomeConfig getByIdRel(Integer id) {
        ShopGoodsIncomeConfigParam param = new ShopGoodsIncomeConfigParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
