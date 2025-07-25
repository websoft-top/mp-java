package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.CompanyContent;
import com.gxwebsoft.common.system.param.CompanyContentParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用详情Mapper
 *
 * @author 科技小王子
 * @since 2024-10-16 13:41:21
 */
public interface CompanyContentMapper extends BaseMapper<CompanyContent> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CompanyContent>
     */
    List<CompanyContent> selectPageRel(@Param("page") IPage<CompanyContent> page,
                             @Param("param") CompanyContentParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CompanyContent> selectListRel(@Param("param") CompanyContentParam param);

}
