package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsMpAdMapper;
import com.gxwebsoft.cms.service.CmsMpAdService;
import com.gxwebsoft.cms.entity.CmsMpAd;
import com.gxwebsoft.cms.param.CmsMpAdParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小程序广告位Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsMpAdServiceImpl extends ServiceImpl<CmsMpAdMapper, CmsMpAd> implements CmsMpAdService {

    @Override
    public PageResult<CmsMpAd> pageRel(CmsMpAdParam param) {
        PageParam<CmsMpAd, CmsMpAdParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsMpAd> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsMpAd> listRel(CmsMpAdParam param) {
        List<CmsMpAd> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsMpAd, CmsMpAdParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsMpAd getByIdRel(Integer adId) {
        CmsMpAdParam param = new CmsMpAdParam();
        param.setAdId(adId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
