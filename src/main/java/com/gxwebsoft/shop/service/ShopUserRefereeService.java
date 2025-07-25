package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopUserReferee;
import com.gxwebsoft.shop.param.ShopUserRefereeParam;

import java.util.List;

/**
 * 用户推荐关系表Service
 *
 * @author 科技小王子
 * @since 2025-03-05 17:05:28
 */
public interface ShopUserRefereeService extends IService<ShopUserReferee> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopUserReferee>
     */
    PageResult<ShopUserReferee> pageRel(ShopUserRefereeParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopUserReferee>
     */
    List<ShopUserReferee> listRel(ShopUserRefereeParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopUserReferee
     */
    ShopUserReferee getByIdRel(Integer id);

}
