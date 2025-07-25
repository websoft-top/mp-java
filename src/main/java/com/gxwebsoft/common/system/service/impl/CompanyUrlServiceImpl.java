package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyUrl;
import com.gxwebsoft.common.system.mapper.CompanyUrlMapper;
import com.gxwebsoft.common.system.param.CompanyUrlParam;
import com.gxwebsoft.common.system.service.CompanyUrlService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用域名Service实现
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Service
public class CompanyUrlServiceImpl extends ServiceImpl<CompanyUrlMapper, CompanyUrl> implements CompanyUrlService {

    @Override
    public PageResult<CompanyUrl> pageRel(CompanyUrlParam param) {
        PageParam<CompanyUrl, CompanyUrlParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CompanyUrl> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CompanyUrl> listRel(CompanyUrlParam param) {
        List<CompanyUrl> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CompanyUrl, CompanyUrlParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CompanyUrl getByIdRel(Integer id) {
        CompanyUrlParam param = new CompanyUrlParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
