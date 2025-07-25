package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopChatMessageMapper;
import com.gxwebsoft.shop.service.ShopChatMessageService;
import com.gxwebsoft.shop.entity.ShopChatMessage;
import com.gxwebsoft.shop.param.ShopChatMessageParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 聊天消息表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopChatMessageServiceImpl extends ServiceImpl<ShopChatMessageMapper, ShopChatMessage> implements ShopChatMessageService {

    @Override
    public PageResult<ShopChatMessage> pageRel(ShopChatMessageParam param) {
        PageParam<ShopChatMessage, ShopChatMessageParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopChatMessage> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopChatMessage> listRel(ShopChatMessageParam param) {
        List<ShopChatMessage> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopChatMessage, ShopChatMessageParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopChatMessage getByIdRel(Integer id) {
        ShopChatMessageParam param = new ShopChatMessageParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
