package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.CompanyGit;
import com.gxwebsoft.common.system.param.CompanyGitParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码仓库Mapper
 *
 * @author 科技小王子
 * @since 2024-10-19 18:08:51
 */
public interface CompanyGitMapper extends BaseMapper<CompanyGit> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CompanyGit>
     */
    List<CompanyGit> selectPageRel(@Param("page") IPage<CompanyGit> page,
                             @Param("param") CompanyGitParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CompanyGit> selectListRel(@Param("param") CompanyGitParam param);

}
