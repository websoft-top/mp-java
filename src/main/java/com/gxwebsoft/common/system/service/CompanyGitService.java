package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyGit;
import com.gxwebsoft.common.system.param.CompanyGitParam;

import java.util.List;

/**
 * 代码仓库Service
 *
 * @author 科技小王子
 * @since 2024-10-19 18:08:51
 */
public interface CompanyGitService extends IService<CompanyGit> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CompanyGit>
     */
    PageResult<CompanyGit> pageRel(CompanyGitParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CompanyGit>
     */
    List<CompanyGit> listRel(CompanyGitParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CompanyGit
     */
    CompanyGit getByIdRel(Integer id);

}
