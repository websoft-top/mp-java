package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsFormRecord;
import com.gxwebsoft.cms.param.CmsFormRecordParam;

import java.util.List;

/**
 * 表单数据记录表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsFormRecordService extends IService<CmsFormRecord> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsFormRecord>
     */
    PageResult<CmsFormRecord> pageRel(CmsFormRecordParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsFormRecord>
     */
    List<CmsFormRecord> listRel(CmsFormRecordParam param);

    /**
     * 根据id查询
     *
     * @param formRecordId ID
     * @return CmsFormRecord
     */
    CmsFormRecord getByIdRel(Integer formRecordId);

}
