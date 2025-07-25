package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsProductUrlMapper;
import com.gxwebsoft.cms.service.CmsProductUrlService;
import com.gxwebsoft.cms.entity.CmsProductUrl;
import com.gxwebsoft.cms.param.CmsProductUrlParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 域名Service实现
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Service
public class CmsProductUrlServiceImpl extends ServiceImpl<CmsProductUrlMapper, CmsProductUrl> implements CmsProductUrlService {

    @Override
    public PageResult<CmsProductUrl> pageRel(CmsProductUrlParam param) {
        PageParam<CmsProductUrl, CmsProductUrlParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time asc");
        List<CmsProductUrl> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsProductUrl> listRel(CmsProductUrlParam param) {
        List<CmsProductUrl> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsProductUrl, CmsProductUrlParam> page = new PageParam<>();
        page.setDefaultOrder("create_time asc");
        return page.sortRecords(list);
    }

    @Override
    public CmsProductUrl getByIdRel(Integer id) {
        CmsProductUrlParam param = new CmsProductUrlParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
