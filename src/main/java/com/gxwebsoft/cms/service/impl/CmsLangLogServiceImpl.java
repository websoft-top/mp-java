package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsLangLogMapper;
import com.gxwebsoft.cms.service.CmsLangLogService;
import com.gxwebsoft.cms.entity.CmsLangLog;
import com.gxwebsoft.cms.param.CmsLangLogParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国际化记录启用Service实现
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
@Service
public class CmsLangLogServiceImpl extends ServiceImpl<CmsLangLogMapper, CmsLangLog> implements CmsLangLogService {

    @Override
    public PageResult<CmsLangLog> pageRel(CmsLangLogParam param) {
        PageParam<CmsLangLog, CmsLangLogParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsLangLog> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsLangLog> listRel(CmsLangLogParam param) {
        List<CmsLangLog> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsLangLog, CmsLangLogParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsLangLog getByIdRel(Integer id) {
        CmsLangLogParam param = new CmsLangLogParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
