package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopCommissionRoleMapper;
import com.gxwebsoft.shop.service.ShopCommissionRoleService;
import com.gxwebsoft.shop.entity.ShopCommissionRole;
import com.gxwebsoft.shop.param.ShopCommissionRoleParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分红角色Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 10:01:15
 */
@Service
public class ShopCommissionRoleServiceImpl extends ServiceImpl<ShopCommissionRoleMapper, ShopCommissionRole> implements ShopCommissionRoleService {

    @Override
    public PageResult<ShopCommissionRole> pageRel(ShopCommissionRoleParam param) {
        PageParam<ShopCommissionRole, ShopCommissionRoleParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopCommissionRole> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopCommissionRole> listRel(ShopCommissionRoleParam param) {
        List<ShopCommissionRole> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopCommissionRole, ShopCommissionRoleParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopCommissionRole getByIdRel(Integer id) {
        ShopCommissionRoleParam param = new ShopCommissionRoleParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
