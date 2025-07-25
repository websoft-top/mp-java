package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyGit;
import com.gxwebsoft.common.system.mapper.CompanyGitMapper;
import com.gxwebsoft.common.system.param.CompanyGitParam;
import com.gxwebsoft.common.system.service.CompanyGitService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代码仓库Service实现
 *
 * @author 科技小王子
 * @since 2024-10-19 18:08:51
 */
@Service
public class CompanyGitServiceImpl extends ServiceImpl<CompanyGitMapper, CompanyGit> implements CompanyGitService {

    @Override
    public PageResult<CompanyGit> pageRel(CompanyGitParam param) {
        PageParam<CompanyGit, CompanyGitParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CompanyGit> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CompanyGit> listRel(CompanyGitParam param) {
        List<CompanyGit> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CompanyGit, CompanyGitParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CompanyGit getByIdRel(Integer id) {
        CompanyGitParam param = new CompanyGitParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
