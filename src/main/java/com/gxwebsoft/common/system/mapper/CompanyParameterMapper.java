package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.CompanyParameter;
import com.gxwebsoft.common.system.param.CompanyParameterParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用参数Mapper
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
public interface CompanyParameterMapper extends BaseMapper<CompanyParameter> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CompanyParameter>
     */
    List<CompanyParameter> selectPageRel(@Param("page") IPage<CompanyParameter> page,
                             @Param("param") CompanyParameterParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CompanyParameter> selectListRel(@Param("param") CompanyParameterParam param);

}
