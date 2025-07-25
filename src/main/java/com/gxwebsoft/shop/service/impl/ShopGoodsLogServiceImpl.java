package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsLogMapper;
import com.gxwebsoft.shop.service.ShopGoodsLogService;
import com.gxwebsoft.shop.entity.ShopGoodsLog;
import com.gxwebsoft.shop.param.ShopGoodsLogParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品日志表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopGoodsLogServiceImpl extends ServiceImpl<ShopGoodsLogMapper, ShopGoodsLog> implements ShopGoodsLogService {

    @Override
    public PageResult<ShopGoodsLog> pageRel(ShopGoodsLogParam param) {
        PageParam<ShopGoodsLog, ShopGoodsLogParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsLog> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsLog> listRel(ShopGoodsLogParam param) {
        List<ShopGoodsLog> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsLog, ShopGoodsLogParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsLog getByIdRel(Integer id) {
        ShopGoodsLogParam param = new ShopGoodsLogParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
