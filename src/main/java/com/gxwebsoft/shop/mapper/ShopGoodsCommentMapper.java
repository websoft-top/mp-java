package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsComment;
import com.gxwebsoft.shop.param.ShopGoodsCommentParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsCommentMapper extends BaseMapper<ShopGoodsComment> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsComment>
     */
    List<ShopGoodsComment> selectPageRel(@Param("page") IPage<ShopGoodsComment> page,
                             @Param("param") ShopGoodsCommentParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsComment> selectListRel(@Param("param") ShopGoodsCommentParam param);

}
