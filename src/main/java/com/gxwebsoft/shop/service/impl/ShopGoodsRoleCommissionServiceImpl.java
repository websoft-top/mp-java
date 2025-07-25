package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsRoleCommissionMapper;
import com.gxwebsoft.shop.service.ShopGoodsRoleCommissionService;
import com.gxwebsoft.shop.entity.ShopGoodsRoleCommission;
import com.gxwebsoft.shop.param.ShopGoodsRoleCommissionParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品绑定角色的分润金额Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 09:53:38
 */
@Service
public class ShopGoodsRoleCommissionServiceImpl extends ServiceImpl<ShopGoodsRoleCommissionMapper, ShopGoodsRoleCommission> implements ShopGoodsRoleCommissionService {

    @Override
    public PageResult<ShopGoodsRoleCommission> pageRel(ShopGoodsRoleCommissionParam param) {
        PageParam<ShopGoodsRoleCommission, ShopGoodsRoleCommissionParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsRoleCommission> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsRoleCommission> listRel(ShopGoodsRoleCommissionParam param) {
        List<ShopGoodsRoleCommission> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsRoleCommission, ShopGoodsRoleCommissionParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsRoleCommission getByIdRel(Integer id) {
        ShopGoodsRoleCommissionParam param = new ShopGoodsRoleCommissionParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
