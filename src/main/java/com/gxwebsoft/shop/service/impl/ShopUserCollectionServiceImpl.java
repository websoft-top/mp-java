package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopUserCollectionMapper;
import com.gxwebsoft.shop.service.ShopUserCollectionService;
import com.gxwebsoft.shop.entity.ShopUserCollection;
import com.gxwebsoft.shop.param.ShopUserCollectionParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的收藏Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Service
public class ShopUserCollectionServiceImpl extends ServiceImpl<ShopUserCollectionMapper, ShopUserCollection> implements ShopUserCollectionService {

    @Override
    public PageResult<ShopUserCollection> pageRel(ShopUserCollectionParam param) {
        PageParam<ShopUserCollection, ShopUserCollectionParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopUserCollection> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopUserCollection> listRel(ShopUserCollectionParam param) {
        List<ShopUserCollection> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopUserCollection, ShopUserCollectionParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopUserCollection getByIdRel(Integer id) {
        ShopUserCollectionParam param = new ShopUserCollectionParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
