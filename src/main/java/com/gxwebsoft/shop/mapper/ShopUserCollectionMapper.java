package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopUserCollection;
import com.gxwebsoft.shop.param.ShopUserCollectionParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 我的收藏Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopUserCollectionMapper extends BaseMapper<ShopUserCollection> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopUserCollection>
     */
    List<ShopUserCollection> selectPageRel(@Param("page") IPage<ShopUserCollection> page,
                             @Param("param") ShopUserCollectionParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopUserCollection> selectListRel(@Param("param") ShopUserCollectionParam param);

}
