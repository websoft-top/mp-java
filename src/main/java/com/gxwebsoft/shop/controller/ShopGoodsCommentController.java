package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsCommentService;
import com.gxwebsoft.shop.entity.ShopGoodsComment;
import com.gxwebsoft.shop.param.ShopGoodsCommentParam;
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
 * 评论表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "评论表管理")
@RestController
@RequestMapping("/api/shop/shop-goods-comment")
public class ShopGoodsCommentController extends BaseController {
    @Resource
    private ShopGoodsCommentService shopGoodsCommentService;

    @ApiOperation("分页查询评论表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsComment>> page(ShopGoodsCommentParam param) {
        // 使用关联查询
        return success(shopGoodsCommentService.pageRel(param));
    }

    @ApiOperation("查询全部评论表")
    @GetMapping()
    public ApiResult<List<ShopGoodsComment>> list(ShopGoodsCommentParam param) {
        // 使用关联查询
        return success(shopGoodsCommentService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsComment:list')")
    @ApiOperation("根据id查询评论表")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsComment> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsCommentService.getByIdRel(id));
    }

    @ApiOperation("添加评论表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsComment shopGoodsComment) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopGoodsComment.setUserId(loginUser.getUserId());
        }
        if (shopGoodsCommentService.save(shopGoodsComment)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改评论表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsComment shopGoodsComment) {
        if (shopGoodsCommentService.updateById(shopGoodsComment)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除评论表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsCommentService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加评论表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsComment> list) {
        if (shopGoodsCommentService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改评论表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsComment> batchParam) {
        if (batchParam.update(shopGoodsCommentService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除评论表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsCommentService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
