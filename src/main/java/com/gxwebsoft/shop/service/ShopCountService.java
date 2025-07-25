package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopCount;
import com.gxwebsoft.shop.param.ShopCountParam;

import java.util.List;

/**
 * 商城销售统计表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopCountService extends IService<ShopCount> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopCount>
     */
    PageResult<ShopCount> pageRel(ShopCountParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopCount>
     */
    List<ShopCount> listRel(ShopCountParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return ShopCount
     */
    ShopCount getByIdRel(Integer id);

}
