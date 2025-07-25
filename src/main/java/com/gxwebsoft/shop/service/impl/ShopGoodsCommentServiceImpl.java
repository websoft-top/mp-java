package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsCommentMapper;
import com.gxwebsoft.shop.service.ShopGoodsCommentService;
import com.gxwebsoft.shop.entity.ShopGoodsComment;
import com.gxwebsoft.shop.param.ShopGoodsCommentParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopGoodsCommentServiceImpl extends ServiceImpl<ShopGoodsCommentMapper, ShopGoodsComment> implements ShopGoodsCommentService {

    @Override
    public PageResult<ShopGoodsComment> pageRel(ShopGoodsCommentParam param) {
        PageParam<ShopGoodsComment, ShopGoodsCommentParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsComment> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsComment> listRel(ShopGoodsCommentParam param) {
        List<ShopGoodsComment> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsComment, ShopGoodsCommentParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsComment getByIdRel(Integer id) {
        ShopGoodsCommentParam param = new ShopGoodsCommentParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
