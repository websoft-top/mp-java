package com.gxwebsoft.cms.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsAdMapper;
import com.gxwebsoft.cms.service.CmsAdService;
import com.gxwebsoft.cms.entity.CmsAd;
import com.gxwebsoft.cms.param.CmsAdParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 广告位Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsAdServiceImpl extends ServiceImpl<CmsAdMapper, CmsAd> implements CmsAdService {

    @Override
    public PageResult<CmsAd> pageRel(CmsAdParam param) {
        PageParam<CmsAd, CmsAdParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time asc");
        List<CmsAd> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsAd> listRel(CmsAdParam param) {
        List<CmsAd> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsAd, CmsAdParam> page = new PageParam<>();
        page.setDefaultOrder("create_time asc");
        return page.sortRecords(list);
    }

    @Override
    public CmsAd getByIdRel(Integer adId) {
        CmsAdParam param = new CmsAdParam();
        param.setAdId(adId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
