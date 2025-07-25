package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopChatMessage;
import com.gxwebsoft.shop.param.ShopChatMessageParam;

import java.util.List;

/**
 * 聊天消息表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopChatMessageService extends IService<ShopChatMessage> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopChatMessage>
     */
    PageResult<ShopChatMessage> pageRel(ShopChatMessageParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopChatMessage>
     */
    List<ShopChatMessage> listRel(ShopChatMessageParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return ShopChatMessage
     */
    ShopChatMessage getByIdRel(Integer id);

}
