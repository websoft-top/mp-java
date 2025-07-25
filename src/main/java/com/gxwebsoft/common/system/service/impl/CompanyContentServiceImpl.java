package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyContent;
import com.gxwebsoft.common.system.mapper.CompanyContentMapper;
import com.gxwebsoft.common.system.param.CompanyContentParam;
import com.gxwebsoft.common.system.service.CompanyContentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用详情Service实现
 *
 * @author 科技小王子
 * @since 2024-10-16 13:41:21
 */
@Service
public class CompanyContentServiceImpl extends ServiceImpl<CompanyContentMapper, CompanyContent> implements CompanyContentService {

    @Override
    public PageResult<CompanyContent> pageRel(CompanyContentParam param) {
        PageParam<CompanyContent, CompanyContentParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CompanyContent> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CompanyContent> listRel(CompanyContentParam param) {
        List<CompanyContent> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CompanyContent, CompanyContentParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CompanyContent getByIdRel(Integer id) {
        CompanyContentParam param = new CompanyContentParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
