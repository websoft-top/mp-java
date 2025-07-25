package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.system.mapper.TenantMapper;
import com.gxwebsoft.common.system.service.TenantService;
import com.gxwebsoft.common.system.entity.Tenant;
import com.gxwebsoft.common.system.param.TenantParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租户Service实现
 *
 * @author 科技小王子
 * @since 2023-07-17 17:49:53
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Override
    public PageResult<Tenant> pageRel(TenantParam param) {
        PageParam<Tenant, TenantParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<Tenant> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<Tenant> listRel(TenantParam param) {
        List<Tenant> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<Tenant, TenantParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public Tenant getByIdRel(Integer tenantId) {
        TenantParam param = new TenantParam();
        return param.getOne(baseMapper.selectListRel(param));
    }

}
