package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.CompanyComment;
import com.gxwebsoft.common.system.param.CompanyCommentParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应用评论Mapper
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
public interface CompanyCommentMapper extends BaseMapper<CompanyComment> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CompanyComment>
     */
    List<CompanyComment> selectPageRel(@Param("page") IPage<CompanyComment> page,
                             @Param("param") CompanyCommentParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CompanyComment> selectListRel(@Param("param") CompanyCommentParam param);

}
