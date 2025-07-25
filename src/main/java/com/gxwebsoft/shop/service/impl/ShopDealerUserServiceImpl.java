package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopDealerUserMapper;
import com.gxwebsoft.shop.service.ShopDealerUserService;
import com.gxwebsoft.shop.entity.ShopDealerUser;
import com.gxwebsoft.shop.param.ShopDealerUserParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分销商用户记录表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopDealerUserServiceImpl extends ServiceImpl<ShopDealerUserMapper, ShopDealerUser> implements ShopDealerUserService {

    @Override
    public PageResult<ShopDealerUser> pageRel(ShopDealerUserParam param) {
        PageParam<ShopDealerUser, ShopDealerUserParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopDealerUser> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopDealerUser> listRel(ShopDealerUserParam param) {
        List<ShopDealerUser> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopDealerUser, ShopDealerUserParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopDealerUser getByIdRel(Integer id) {
        ShopDealerUserParam param = new ShopDealerUserParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
