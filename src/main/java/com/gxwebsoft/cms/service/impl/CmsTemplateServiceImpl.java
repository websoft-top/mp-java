package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsTemplateMapper;
import com.gxwebsoft.cms.service.CmsTemplateService;
import com.gxwebsoft.cms.entity.CmsTemplate;
import com.gxwebsoft.cms.param.CmsTemplateParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 网站模版Service实现
 *
 * @author 科技小王子
 * @since 2025-01-21 14:21:16
 */
@Service
public class CmsTemplateServiceImpl extends ServiceImpl<CmsTemplateMapper, CmsTemplate> implements CmsTemplateService {

    @Override
    public PageResult<CmsTemplate> pageRel(CmsTemplateParam param) {
        PageParam<CmsTemplate, CmsTemplateParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<CmsTemplate> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsTemplate> listRel(CmsTemplateParam param) {
        List<CmsTemplate> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsTemplate, CmsTemplateParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsTemplate getByIdRel(Integer id) {
        CmsTemplateParam param = new CmsTemplateParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
