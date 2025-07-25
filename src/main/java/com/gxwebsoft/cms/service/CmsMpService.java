package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsMp;
import com.gxwebsoft.cms.param.CmsMpParam;

import java.util.List;

/**
 * 小程序信息Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpService extends IService<CmsMp> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsMp>
     */
    PageResult<CmsMp> pageRel(CmsMpParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsMp>
     */
    List<CmsMp> listRel(CmsMpParam param);

    /**
     * 根据id查询
     *
     * @param mpId ID
     * @return CmsMp
     */
    CmsMp getByIdRel(Integer mpId);

}
