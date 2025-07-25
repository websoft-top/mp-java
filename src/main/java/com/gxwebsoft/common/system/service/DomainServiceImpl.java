package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Domain;
import com.gxwebsoft.common.system.mapper.DomainMapper;
import com.gxwebsoft.common.system.param.DomainParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 授权域名Service实现
 *
 * @author 科技小王子
 * @since 2024-09-19 23:56:33
 */
@Service
public class DomainServiceImpl extends ServiceImpl<DomainMapper, Domain> implements DomainService {

    @Override
    public PageResult<Domain> pageRel(DomainParam param) {
        PageParam<Domain, DomainParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<Domain> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<Domain> listRel(DomainParam param) {
        List<Domain> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<Domain, DomainParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public Domain getByIdRel(Integer id) {
        DomainParam param = new DomainParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
