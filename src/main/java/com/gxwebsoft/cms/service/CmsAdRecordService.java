package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsAdRecord;
import com.gxwebsoft.cms.param.CmsAdRecordParam;

import java.util.List;

/**
 * 广告图片Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsAdRecordService extends IService<CmsAdRecord> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsAdRecord>
     */
    PageResult<CmsAdRecord> pageRel(CmsAdRecordParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsAdRecord>
     */
    List<CmsAdRecord> listRel(CmsAdRecordParam param);

    /**
     * 根据id查询
     *
     * @param adRecordId ID
     * @return CmsAdRecord
     */
    CmsAdRecord getByIdRel(Integer adRecordId);

}
