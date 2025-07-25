package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopDealerReferee;
import com.gxwebsoft.shop.param.ShopDealerRefereeParam;

import java.util.List;

/**
 * 分销商推荐关系表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerRefereeService extends IService<ShopDealerReferee> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopDealerReferee>
     */
    PageResult<ShopDealerReferee> pageRel(ShopDealerRefereeParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopDealerReferee>
     */
    List<ShopDealerReferee> listRel(ShopDealerRefereeParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopDealerReferee
     */
    ShopDealerReferee getByIdRel(Integer id);

}
