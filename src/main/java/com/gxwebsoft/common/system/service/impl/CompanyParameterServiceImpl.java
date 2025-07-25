package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyParameter;
import com.gxwebsoft.common.system.mapper.CompanyParameterMapper;
import com.gxwebsoft.common.system.param.CompanyParameterParam;
import com.gxwebsoft.common.system.service.CompanyParameterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用参数Service实现
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Service
public class CompanyParameterServiceImpl extends ServiceImpl<CompanyParameterMapper, CompanyParameter> implements CompanyParameterService {

    @Override
    public PageResult<CompanyParameter> pageRel(CompanyParameterParam param) {
        PageParam<CompanyParameter, CompanyParameterParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CompanyParameter> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CompanyParameter> listRel(CompanyParameterParam param) {
        List<CompanyParameter> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CompanyParameter, CompanyParameterParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CompanyParameter getByIdRel(Integer id) {
        CompanyParameterParam param = new CompanyParameterParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
