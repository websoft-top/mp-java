package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyComment;
import com.gxwebsoft.common.system.param.CompanyCommentParam;

import java.util.List;

/**
 * 应用评论Service
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
public interface CompanyCommentService extends IService<CompanyComment> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CompanyComment>
     */
    PageResult<CompanyComment> pageRel(CompanyCommentParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CompanyComment>
     */
    List<CompanyComment> listRel(CompanyCommentParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CompanyComment
     */
    CompanyComment getByIdRel(Integer id);

}
