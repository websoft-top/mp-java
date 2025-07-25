package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopChatConversationMapper;
import com.gxwebsoft.shop.service.ShopChatConversationService;
import com.gxwebsoft.shop.entity.ShopChatConversation;
import com.gxwebsoft.shop.param.ShopChatConversationParam;
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
public class ShopChatConversationServiceImpl extends ServiceImpl<ShopChatConversationMapper, ShopChatConversation> implements ShopChatConversationService {

    @Override
    public PageResult<ShopChatConversation> pageRel(ShopChatConversationParam param) {
        PageParam<ShopChatConversation, ShopChatConversationParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopChatConversation> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopChatConversation> listRel(ShopChatConversationParam param) {
        List<ShopChatConversation> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopChatConversation, ShopChatConversationParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopChatConversation getByIdRel(Integer id) {
        ShopChatConversationParam param = new ShopChatConversationParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
