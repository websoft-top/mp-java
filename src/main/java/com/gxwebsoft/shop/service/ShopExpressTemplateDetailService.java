package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopExpressTemplateDetail;
import com.gxwebsoft.shop.param.ShopExpressTemplateDetailParam;

import java.util.List;

/**
 * 运费模板Service
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
public interface ShopExpressTemplateDetailService extends IService<ShopExpressTemplateDetail> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopExpressTemplateDetail>
     */
    PageResult<ShopExpressTemplateDetail> pageRel(ShopExpressTemplateDetailParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopExpressTemplateDetail>
     */
    List<ShopExpressTemplateDetail> listRel(ShopExpressTemplateDetailParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopExpressTemplateDetail
     */
    ShopExpressTemplateDetail getByIdRel(Integer id);

}
