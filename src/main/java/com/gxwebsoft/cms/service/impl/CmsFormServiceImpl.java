package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsFormMapper;
import com.gxwebsoft.cms.service.CmsFormService;
import com.gxwebsoft.cms.entity.CmsForm;
import com.gxwebsoft.cms.param.CmsFormParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表单设计表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsFormServiceImpl extends ServiceImpl<CmsFormMapper, CmsForm> implements CmsFormService {

    @Override
    public PageResult<CmsForm> pageRel(CmsFormParam param) {
        PageParam<CmsForm, CmsFormParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsForm> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsForm> listRel(CmsFormParam param) {
        List<CmsForm> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsForm, CmsFormParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsForm getByIdRel(Integer formId) {
        CmsFormParam param = new CmsFormParam();
        param.setFormId(formId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
