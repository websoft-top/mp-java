package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopChatConversation;
import com.gxwebsoft.shop.param.ShopChatConversationParam;

import java.util.List;

/**
 * 聊天消息表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopChatConversationService extends IService<ShopChatConversation> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopChatConversation>
     */
    PageResult<ShopChatConversation> pageRel(ShopChatConversationParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopChatConversation>
     */
    List<ShopChatConversation> listRel(ShopChatConversationParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return ShopChatConversation
     */
    ShopChatConversation getByIdRel(Integer id);

}
