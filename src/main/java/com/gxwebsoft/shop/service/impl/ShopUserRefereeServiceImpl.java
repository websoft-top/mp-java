package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopUserRefereeMapper;
import com.gxwebsoft.shop.service.ShopUserRefereeService;
import com.gxwebsoft.shop.entity.ShopUserReferee;
import com.gxwebsoft.shop.param.ShopUserRefereeParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户推荐关系表Service实现
 *
 * @author 科技小王子
 * @since 2025-03-05 17:05:28
 */
@Service
public class ShopUserRefereeServiceImpl extends ServiceImpl<ShopUserRefereeMapper, ShopUserReferee> implements ShopUserRefereeService {

    @Override
    public PageResult<ShopUserReferee> pageRel(ShopUserRefereeParam param) {
        PageParam<ShopUserReferee, ShopUserRefereeParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopUserReferee> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopUserReferee> listRel(ShopUserRefereeParam param) {
        List<ShopUserReferee> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopUserReferee, ShopUserRefereeParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopUserReferee getByIdRel(Integer id) {
        ShopUserRefereeParam param = new ShopUserRefereeParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
