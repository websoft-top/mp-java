package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsMpField;
import com.gxwebsoft.cms.param.CmsMpFieldParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序配置Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpFieldMapper extends BaseMapper<CmsMpField> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsMpField>
     */
    List<CmsMpField> selectPageRel(@Param("page") IPage<CmsMpField> page,
                             @Param("param") CmsMpFieldParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsMpField> selectListRel(@Param("param") CmsMpFieldParam param);

}
