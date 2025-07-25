package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopWechatDepositMapper;
import com.gxwebsoft.shop.service.ShopWechatDepositService;
import com.gxwebsoft.shop.entity.ShopWechatDeposit;
import com.gxwebsoft.shop.param.ShopWechatDepositParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 押金Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Service
public class ShopWechatDepositServiceImpl extends ServiceImpl<ShopWechatDepositMapper, ShopWechatDeposit> implements ShopWechatDepositService {

    @Override
    public PageResult<ShopWechatDeposit> pageRel(ShopWechatDepositParam param) {
        PageParam<ShopWechatDeposit, ShopWechatDepositParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopWechatDeposit> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopWechatDeposit> listRel(ShopWechatDepositParam param) {
        List<ShopWechatDeposit> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopWechatDeposit, ShopWechatDepositParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopWechatDeposit getByIdRel(Integer id) {
        ShopWechatDepositParam param = new ShopWechatDepositParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
