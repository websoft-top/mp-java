package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.Plug;
import com.gxwebsoft.common.system.param.PlugParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 插件扩展Mapper
 *
 * @author 科技小王子
 * @since 2023-05-18 11:57:37
 */
public interface PlugMapper extends BaseMapper<Plug> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<Plug>
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Plug> selectPageRel(@Param("page") IPage<Plug> page,
                             @Param("param") PlugParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<Plug> selectListRel(@Param("param") PlugParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<Plug> getMenuByClone(@Param("param") PlugParam param);

}
