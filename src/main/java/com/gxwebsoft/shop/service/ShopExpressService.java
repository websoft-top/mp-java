package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopExpress;
import com.gxwebsoft.shop.param.ShopExpressParam;

import java.util.List;

/**
 * 物流公司Service
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
public interface ShopExpressService extends IService<ShopExpress> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopExpress>
     */
    PageResult<ShopExpress> pageRel(ShopExpressParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopExpress>
     */
    List<ShopExpress> listRel(ShopExpressParam param);

    /**
     * 根据id查询
     *
     * @param expressId 物流公司ID
     * @return ShopExpress
     */
    ShopExpress getByIdRel(Integer expressId);

}
