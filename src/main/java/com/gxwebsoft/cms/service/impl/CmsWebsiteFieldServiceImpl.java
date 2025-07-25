package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsWebsiteFieldMapper;
import com.gxwebsoft.cms.service.CmsWebsiteFieldService;
import com.gxwebsoft.cms.entity.CmsWebsiteField;
import com.gxwebsoft.cms.param.CmsWebsiteFieldParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用参数Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Service
public class CmsWebsiteFieldServiceImpl extends ServiceImpl<CmsWebsiteFieldMapper, CmsWebsiteField> implements CmsWebsiteFieldService {

    @Override
    public PageResult<CmsWebsiteField> pageRel(CmsWebsiteFieldParam param) {
        PageParam<CmsWebsiteField, CmsWebsiteFieldParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time asc");
        List<CmsWebsiteField> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsWebsiteField> listRel(CmsWebsiteFieldParam param) {
        List<CmsWebsiteField> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsWebsiteField, CmsWebsiteFieldParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time asc");
        return page.sortRecords(list);
    }

    @Override
    public CmsWebsiteField getByIdRel(Integer id) {
        CmsWebsiteFieldParam param = new CmsWebsiteFieldParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
