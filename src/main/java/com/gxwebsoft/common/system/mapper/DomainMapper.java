package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.Domain;
import com.gxwebsoft.common.system.param.DomainParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 授权域名Mapper
 *
 * @author 科技小王子
 * @since 2024-09-19 23:56:33
 */
public interface DomainMapper extends BaseMapper<Domain> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<Domain>
     */
    List<Domain> selectPageRel(@Param("page") IPage<Domain> page,
                             @Param("param") DomainParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<Domain> selectListRel(@Param("param") DomainParam param);

}
