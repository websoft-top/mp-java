package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopDealerSetting;
import com.gxwebsoft.shop.param.ShopDealerSettingParam;

import java.util.List;

/**
 * 分销商设置表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerSettingService extends IService<ShopDealerSetting> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopDealerSetting>
     */
    PageResult<ShopDealerSetting> pageRel(ShopDealerSettingParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopDealerSetting>
     */
    List<ShopDealerSetting> listRel(ShopDealerSettingParam param);

    /**
     * 根据id查询
     *
     * @param key 设置项标示
     * @return ShopDealerSetting
     */
    ShopDealerSetting getByIdRel(String key);

}
