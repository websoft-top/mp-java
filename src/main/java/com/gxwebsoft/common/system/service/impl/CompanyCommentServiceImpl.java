package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyComment;
import com.gxwebsoft.common.system.mapper.CompanyCommentMapper;
import com.gxwebsoft.common.system.param.CompanyCommentParam;
import com.gxwebsoft.common.system.service.CompanyCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用评论Service实现
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Service
public class CompanyCommentServiceImpl extends ServiceImpl<CompanyCommentMapper, CompanyComment> implements CompanyCommentService {

    @Override
    public PageResult<CompanyComment> pageRel(CompanyCommentParam param) {
        PageParam<CompanyComment, CompanyCommentParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CompanyComment> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CompanyComment> listRel(CompanyCommentParam param) {
        List<CompanyComment> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CompanyComment, CompanyCommentParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CompanyComment getByIdRel(Integer id) {
        CompanyCommentParam param = new CompanyCommentParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
