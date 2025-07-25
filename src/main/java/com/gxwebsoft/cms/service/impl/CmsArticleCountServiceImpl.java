package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsArticleCountMapper;
import com.gxwebsoft.cms.service.CmsArticleCountService;
import com.gxwebsoft.cms.entity.CmsArticleCount;
import com.gxwebsoft.cms.param.CmsArticleCountParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 点赞文章Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsArticleCountServiceImpl extends ServiceImpl<CmsArticleCountMapper, CmsArticleCount> implements CmsArticleCountService {

    @Override
    public PageResult<CmsArticleCount> pageRel(CmsArticleCountParam param) {
        PageParam<CmsArticleCount, CmsArticleCountParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsArticleCount> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsArticleCount> listRel(CmsArticleCountParam param) {
        List<CmsArticleCount> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsArticleCount, CmsArticleCountParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsArticleCount getByIdRel(Integer id) {
        CmsArticleCountParam param = new CmsArticleCountParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
