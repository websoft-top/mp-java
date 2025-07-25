package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Company;
import com.gxwebsoft.common.system.mapper.CompanyMapper;
import com.gxwebsoft.common.system.param.CompanyParam;
import com.gxwebsoft.common.system.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业信息Service实现
 *
 * @author 科技小王子
 * @since 2023-05-27 14:57:34
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Override
    public PageResult<Company> pageRel(CompanyParam param) {
        PageParam<Company, CompanyParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number desc,create_time desc");
        List<Company> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<Company> listRel(CompanyParam param) {
        List<Company> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<Company, CompanyParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public Company getByIdRel(Integer companyId) {
        CompanyParam param = new CompanyParam();
        param.setCompanyId(companyId);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public Company getByTenantIdRel(Integer tenantId) {
      CompanyParam param = new CompanyParam();
//      final Company one = param.getOne(baseMapper.selectListRel(param));
      param.setAuthoritative(true);
      return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public PageResult<Company> pageRelAll(CompanyParam param) {
      PageParam<Company, CompanyParam> page = new PageParam<>(param);
      page.setDefaultOrder("sort_number desc,create_time desc");
      List<Company> list = baseMapper.selectPageRelAll(page, param);
      return new PageResult<>(list, page.getTotal());
    }

    @Override
    public void updateByCompanyId(Company company) {
      baseMapper.updateByCompanyId(company);
    }

    @Override
    public boolean removeCompanyAll(Integer companyId){
      if (baseMapper.removeCompanyAll(companyId)) {
        return true;
      }
      return false;
    }

    @Override
    public boolean undeleteAll(Integer companyId){
      if (baseMapper.undeleteAll(companyId)) {
        return true;
      }
      return false;
    }



}
