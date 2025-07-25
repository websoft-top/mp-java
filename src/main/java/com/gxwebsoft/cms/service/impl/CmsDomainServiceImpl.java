package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsDomainMapper;
import com.gxwebsoft.cms.service.CmsDomainService;
import com.gxwebsoft.cms.entity.CmsDomain;
import com.gxwebsoft.cms.param.CmsDomainParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 网站域名记录表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Service
public class CmsDomainServiceImpl extends ServiceImpl<CmsDomainMapper, CmsDomain> implements CmsDomainService {

    @Override
    public PageResult<CmsDomain> pageRel(CmsDomainParam param) {
        PageParam<CmsDomain, CmsDomainParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsDomain> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsDomain> listRel(CmsDomainParam param) {
        List<CmsDomain> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsDomain, CmsDomainParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsDomain getByIdRel(Integer id) {
        CmsDomainParam param = new CmsDomainParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
