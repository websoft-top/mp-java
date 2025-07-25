package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsArticleCategoryMapper;
import com.gxwebsoft.cms.service.CmsArticleCategoryService;
import com.gxwebsoft.cms.entity.CmsArticleCategory;
import com.gxwebsoft.cms.param.CmsArticleCategoryParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章分类表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsArticleCategoryServiceImpl extends ServiceImpl<CmsArticleCategoryMapper, CmsArticleCategory> implements CmsArticleCategoryService {

    @Override
    public PageResult<CmsArticleCategory> pageRel(CmsArticleCategoryParam param) {
        PageParam<CmsArticleCategory, CmsArticleCategoryParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsArticleCategory> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsArticleCategory> listRel(CmsArticleCategoryParam param) {
        List<CmsArticleCategory> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsArticleCategory, CmsArticleCategoryParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsArticleCategory getByIdRel(Integer categoryId) {
        CmsArticleCategoryParam param = new CmsArticleCategoryParam();
        param.setCategoryId(categoryId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
