package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopCategoryMapper;
import com.gxwebsoft.shop.service.ShopCategoryService;
import com.gxwebsoft.shop.entity.ShopCategory;
import com.gxwebsoft.shop.param.ShopCategoryParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类Service实现
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
@Service
public class ShopCategoryServiceImpl extends ServiceImpl<ShopCategoryMapper, ShopCategory> implements ShopCategoryService {

    @Override
    public PageResult<ShopCategory> pageRel(ShopCategoryParam param) {
        PageParam<ShopCategory, ShopCategoryParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopCategory> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopCategory> listRel(ShopCategoryParam param) {
        List<ShopCategory> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopCategory, ShopCategoryParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopCategory getByIdRel(Integer id) {
        ShopCategoryParam param = new ShopCategoryParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
