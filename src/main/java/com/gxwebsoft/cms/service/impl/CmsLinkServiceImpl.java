package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsLinkMapper;
import com.gxwebsoft.cms.service.CmsLinkService;
import com.gxwebsoft.cms.entity.CmsLink;
import com.gxwebsoft.cms.param.CmsLinkParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 常用链接Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsLinkServiceImpl extends ServiceImpl<CmsLinkMapper, CmsLink> implements CmsLinkService {

    @Override
    public PageResult<CmsLink> pageRel(CmsLinkParam param) {
        PageParam<CmsLink, CmsLinkParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc,create_time asc");
        List<CmsLink> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsLink> listRel(CmsLinkParam param) {
        List<CmsLink> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsLink, CmsLinkParam> page = new PageParam<>();
        page.setDefaultOrder("create_time asc");
        return page.sortRecords(list);
    }

    @Override
    public CmsLink getByIdRel(Integer id) {
        CmsLinkParam param = new CmsLinkParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
