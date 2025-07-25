package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.CompanyUrl;
import com.gxwebsoft.common.system.param.CompanyUrlParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用域名Mapper
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
public interface CompanyUrlMapper extends BaseMapper<CompanyUrl> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CompanyUrl>
     */
    List<CompanyUrl> selectPageRel(@Param("page") IPage<CompanyUrl> page,
                             @Param("param") CompanyUrlParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CompanyUrl> selectListRel(@Param("param") CompanyUrlParam param);

}
