package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsArticleCommentMapper;
import com.gxwebsoft.cms.service.CmsArticleCommentService;
import com.gxwebsoft.cms.entity.CmsArticleComment;
import com.gxwebsoft.cms.param.CmsArticleCommentParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章评论表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsArticleCommentServiceImpl extends ServiceImpl<CmsArticleCommentMapper, CmsArticleComment> implements CmsArticleCommentService {

    @Override
    public PageResult<CmsArticleComment> pageRel(CmsArticleCommentParam param) {
        PageParam<CmsArticleComment, CmsArticleCommentParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsArticleComment> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsArticleComment> listRel(CmsArticleCommentParam param) {
        List<CmsArticleComment> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsArticleComment, CmsArticleCommentParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsArticleComment getByIdRel(Integer commentId) {
        CmsArticleCommentParam param = new CmsArticleCommentParam();
        param.setCommentId(commentId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
