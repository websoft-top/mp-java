package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopOrderExtractMapper;
import com.gxwebsoft.shop.service.ShopOrderExtractService;
import com.gxwebsoft.shop.entity.ShopOrderExtract;
import com.gxwebsoft.shop.param.ShopOrderExtractParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自提订单联系方式Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopOrderExtractServiceImpl extends ServiceImpl<ShopOrderExtractMapper, ShopOrderExtract> implements ShopOrderExtractService {

    @Override
    public PageResult<ShopOrderExtract> pageRel(ShopOrderExtractParam param) {
        PageParam<ShopOrderExtract, ShopOrderExtractParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopOrderExtract> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopOrderExtract> listRel(ShopOrderExtractParam param) {
        List<ShopOrderExtract> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopOrderExtract, ShopOrderExtractParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopOrderExtract getByIdRel(Integer id) {
        ShopOrderExtractParam param = new ShopOrderExtractParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
