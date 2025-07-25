package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopOrderExtract;
import com.gxwebsoft.shop.param.ShopOrderExtractParam;

import java.util.List;

/**
 * 自提订单联系方式Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderExtractService extends IService<ShopOrderExtract> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopOrderExtract>
     */
    PageResult<ShopOrderExtract> pageRel(ShopOrderExtractParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopOrderExtract>
     */
    List<ShopOrderExtract> listRel(ShopOrderExtractParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopOrderExtract
     */
    ShopOrderExtract getByIdRel(Integer id);

}
