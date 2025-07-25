package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsMpField;
import com.gxwebsoft.cms.param.CmsMpFieldParam;

import java.util.List;

/**
 * 小程序配置Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpFieldService extends IService<CmsMpField> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsMpField>
     */
    PageResult<CmsMpField> pageRel(CmsMpFieldParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsMpField>
     */
    List<CmsMpField> listRel(CmsMpFieldParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CmsMpField
     */
    CmsMpField getByIdRel(Integer id);

}
