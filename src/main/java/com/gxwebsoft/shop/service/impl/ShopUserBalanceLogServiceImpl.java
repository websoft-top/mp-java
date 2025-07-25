package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopUserBalanceLogMapper;
import com.gxwebsoft.shop.service.ShopUserBalanceLogService;
import com.gxwebsoft.shop.entity.ShopUserBalanceLog;
import com.gxwebsoft.shop.param.ShopUserBalanceLogParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户余额变动明细表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Service
public class ShopUserBalanceLogServiceImpl extends ServiceImpl<ShopUserBalanceLogMapper, ShopUserBalanceLog> implements ShopUserBalanceLogService {

    @Override
    public PageResult<ShopUserBalanceLog> pageRel(ShopUserBalanceLogParam param) {
        PageParam<ShopUserBalanceLog, ShopUserBalanceLogParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopUserBalanceLog> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopUserBalanceLog> listRel(ShopUserBalanceLogParam param) {
        List<ShopUserBalanceLog> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopUserBalanceLog, ShopUserBalanceLogParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopUserBalanceLog getByIdRel(Integer logId) {
        ShopUserBalanceLogParam param = new ShopUserBalanceLogParam();
        param.setLogId(logId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
