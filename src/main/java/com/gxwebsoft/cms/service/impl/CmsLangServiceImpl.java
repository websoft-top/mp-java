package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsLangMapper;
import com.gxwebsoft.cms.service.CmsLangService;
import com.gxwebsoft.cms.entity.CmsLang;
import com.gxwebsoft.cms.param.CmsLangParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国际化Service实现
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
@Service
public class CmsLangServiceImpl extends ServiceImpl<CmsLangMapper, CmsLang> implements CmsLangService {

    @Override
    public PageResult<CmsLang> pageRel(CmsLangParam param) {
        PageParam<CmsLang, CmsLangParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<CmsLang> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsLang> listRel(CmsLangParam param) {
        List<CmsLang> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsLang, CmsLangParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsLang getByIdRel(Integer id) {
        CmsLangParam param = new CmsLangParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
