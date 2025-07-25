package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopDealerRefereeMapper;
import com.gxwebsoft.shop.service.ShopDealerRefereeService;
import com.gxwebsoft.shop.entity.ShopDealerReferee;
import com.gxwebsoft.shop.param.ShopDealerRefereeParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分销商推荐关系表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopDealerRefereeServiceImpl extends ServiceImpl<ShopDealerRefereeMapper, ShopDealerReferee> implements ShopDealerRefereeService {

    @Override
    public PageResult<ShopDealerReferee> pageRel(ShopDealerRefereeParam param) {
        PageParam<ShopDealerReferee, ShopDealerRefereeParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopDealerReferee> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopDealerReferee> listRel(ShopDealerRefereeParam param) {
        List<ShopDealerReferee> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopDealerReferee, ShopDealerRefereeParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopDealerReferee getByIdRel(Integer id) {
        ShopDealerRefereeParam param = new ShopDealerRefereeParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
