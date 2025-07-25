package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopChatConversationService;
import com.gxwebsoft.shop.entity.ShopChatConversation;
import com.gxwebsoft.shop.param.ShopChatConversationParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 聊天消息表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "聊天消息表管理")
@RestController
@RequestMapping("/api/shop/shop-chat-conversation")
public class ShopChatConversationController extends BaseController {
    @Resource
    private ShopChatConversationService shopChatConversationService;

    @ApiOperation("分页查询聊天消息表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopChatConversation>> page(ShopChatConversationParam param) {
        // 使用关联查询
        return success(shopChatConversationService.pageRel(param));
    }

    @ApiOperation("查询全部聊天消息表")
    @GetMapping()
    public ApiResult<List<ShopChatConversation>> list(ShopChatConversationParam param) {
        // 使用关联查询
        return success(shopChatConversationService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopChatConversation:list')")
    @ApiOperation("根据id查询聊天消息表")
    @GetMapping("/{id}")
    public ApiResult<ShopChatConversation> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopChatConversationService.getByIdRel(id));
    }

    @ApiOperation("添加聊天消息表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopChatConversation shopChatConversation) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopChatConversation.setUserId(loginUser.getUserId());
        }
        if (shopChatConversationService.save(shopChatConversation)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改聊天消息表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopChatConversation shopChatConversation) {
        if (shopChatConversationService.updateById(shopChatConversation)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除聊天消息表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopChatConversationService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加聊天消息表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopChatConversation> list) {
        if (shopChatConversationService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改聊天消息表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopChatConversation> batchParam) {
        if (batchParam.update(shopChatConversationService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除聊天消息表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopChatConversationService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
