package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsArticleLikeMapper;
import com.gxwebsoft.cms.service.CmsArticleLikeService;
import com.gxwebsoft.cms.entity.CmsArticleLike;
import com.gxwebsoft.cms.param.CmsArticleLikeParam;
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
public class CmsArticleLikeServiceImpl extends ServiceImpl<CmsArticleLikeMapper, CmsArticleLike> implements CmsArticleLikeService {

    @Override
    public PageResult<CmsArticleLike> pageRel(CmsArticleLikeParam param) {
        PageParam<CmsArticleLike, CmsArticleLikeParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsArticleLike> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsArticleLike> listRel(CmsArticleLikeParam param) {
        List<CmsArticleLike> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsArticleLike, CmsArticleLikeParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsArticleLike getByIdRel(Integer id) {
        CmsArticleLikeParam param = new CmsArticleLikeParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
