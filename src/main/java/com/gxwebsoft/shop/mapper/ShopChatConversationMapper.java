package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopChatConversation;
import com.gxwebsoft.shop.param.ShopChatConversationParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天消息表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopChatConversationMapper extends BaseMapper<ShopChatConversation> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopChatConversation>
     */
    List<ShopChatConversation> selectPageRel(@Param("page") IPage<ShopChatConversation> page,
                             @Param("param") ShopChatConversationParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopChatConversation> selectListRel(@Param("param") ShopChatConversationParam param);

}
