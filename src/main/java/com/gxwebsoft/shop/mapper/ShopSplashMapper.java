package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopSplash;
import com.gxwebsoft.shop.param.ShopSplashParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开屏广告Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopSplashMapper extends BaseMapper<ShopSplash> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopSplash>
     */
    List<ShopSplash> selectPageRel(@Param("page") IPage<ShopSplash> page,
                             @Param("param") ShopSplashParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopSplash> selectListRel(@Param("param") ShopSplashParam param);

}
