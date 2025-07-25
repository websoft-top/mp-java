package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopExpressTemplate;
import com.gxwebsoft.shop.param.ShopExpressTemplateParam;

import java.util.List;

/**
 * 运费模板Service
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
public interface ShopExpressTemplateService extends IService<ShopExpressTemplate> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopExpressTemplate>
     */
    PageResult<ShopExpressTemplate> pageRel(ShopExpressTemplateParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopExpressTemplate>
     */
    List<ShopExpressTemplate> listRel(ShopExpressTemplateParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopExpressTemplate
     */
    ShopExpressTemplate getByIdRel(Integer id);

}
