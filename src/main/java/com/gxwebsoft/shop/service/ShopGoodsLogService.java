package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsLog;
import com.gxwebsoft.shop.param.ShopGoodsLogParam;

import java.util.List;

/**
 * 商品日志表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsLogService extends IService<ShopGoodsLog> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsLog>
     */
    PageResult<ShopGoodsLog> pageRel(ShopGoodsLogParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsLog>
     */
    List<ShopGoodsLog> listRel(ShopGoodsLogParam param);

    /**
     * 根据id查询
     *
     * @param id 统计ID
     * @return ShopGoodsLog
     */
    ShopGoodsLog getByIdRel(Integer id);

}
