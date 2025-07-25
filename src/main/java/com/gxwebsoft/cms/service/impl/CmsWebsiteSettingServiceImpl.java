package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsWebsiteSettingMapper;
import com.gxwebsoft.cms.service.CmsWebsiteSettingService;
import com.gxwebsoft.cms.entity.CmsWebsiteSetting;
import com.gxwebsoft.cms.param.CmsWebsiteSettingParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 网站设置Service实现
 *
 * @author 科技小王子
 * @since 2025-02-19 01:35:44
 */
@Service
public class CmsWebsiteSettingServiceImpl extends ServiceImpl<CmsWebsiteSettingMapper, CmsWebsiteSetting> implements CmsWebsiteSettingService {

    @Override
    public PageResult<CmsWebsiteSetting> pageRel(CmsWebsiteSettingParam param) {
        PageParam<CmsWebsiteSetting, CmsWebsiteSettingParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<CmsWebsiteSetting> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsWebsiteSetting> listRel(CmsWebsiteSettingParam param) {
        List<CmsWebsiteSetting> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsWebsiteSetting, CmsWebsiteSettingParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsWebsiteSetting getByIdRel(Integer id) {
        CmsWebsiteSettingParam param = new CmsWebsiteSettingParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
