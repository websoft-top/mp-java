package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopDealerSettingMapper;
import com.gxwebsoft.shop.service.ShopDealerSettingService;
import com.gxwebsoft.shop.entity.ShopDealerSetting;
import com.gxwebsoft.shop.param.ShopDealerSettingParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分销商设置表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopDealerSettingServiceImpl extends ServiceImpl<ShopDealerSettingMapper, ShopDealerSetting> implements ShopDealerSettingService {

    @Override
    public PageResult<ShopDealerSetting> pageRel(ShopDealerSettingParam param) {
        PageParam<ShopDealerSetting, ShopDealerSettingParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopDealerSetting> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopDealerSetting> listRel(ShopDealerSettingParam param) {
        List<ShopDealerSetting> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopDealerSetting, ShopDealerSettingParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopDealerSetting getByIdRel(String key) {
        ShopDealerSettingParam param = new ShopDealerSettingParam();
        param.setKey(key);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
