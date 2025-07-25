package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopSplash;
import com.gxwebsoft.shop.param.ShopSplashParam;

import java.util.List;

/**
 * 开屏广告Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopSplashService extends IService<ShopSplash> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopSplash>
     */
    PageResult<ShopSplash> pageRel(ShopSplashParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopSplash>
     */
    List<ShopSplash> listRel(ShopSplashParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopSplash
     */
    ShopSplash getByIdRel(Integer id);

}
