package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopUserCollection;
import com.gxwebsoft.shop.param.ShopUserCollectionParam;

import java.util.List;

/**
 * 我的收藏Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopUserCollectionService extends IService<ShopUserCollection> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopUserCollection>
     */
    PageResult<ShopUserCollection> pageRel(ShopUserCollectionParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopUserCollection>
     */
    List<ShopUserCollection> listRel(ShopUserCollectionParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopUserCollection
     */
    ShopUserCollection getByIdRel(Integer id);

}
