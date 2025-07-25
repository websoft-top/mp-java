package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsRelationService;
import com.gxwebsoft.shop.entity.ShopGoodsRelation;
import com.gxwebsoft.shop.param.ShopGoodsRelationParam;
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
 * 商品点赞和收藏表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商品点赞和收藏表管理")
@RestController
@RequestMapping("/api/shop/shop-goods-relation")
public class ShopGoodsRelationController extends BaseController {
    @Resource
    private ShopGoodsRelationService shopGoodsRelationService;

    @ApiOperation("分页查询商品点赞和收藏表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsRelation>> page(ShopGoodsRelationParam param) {
        // 使用关联查询
        return success(shopGoodsRelationService.pageRel(param));
    }

    @ApiOperation("查询全部商品点赞和收藏表")
    @GetMapping()
    public ApiResult<List<ShopGoodsRelation>> list(ShopGoodsRelationParam param) {
        // 使用关联查询
        return success(shopGoodsRelationService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsRelation:list')")
    @ApiOperation("根据id查询商品点赞和收藏表")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsRelation> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsRelationService.getByIdRel(id));
    }

    @ApiOperation("添加商品点赞和收藏表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsRelation shopGoodsRelation) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopGoodsRelation.setUserId(loginUser.getUserId());
        }
        if (shopGoodsRelationService.save(shopGoodsRelation)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商品点赞和收藏表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsRelation shopGoodsRelation) {
        if (shopGoodsRelationService.updateById(shopGoodsRelation)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商品点赞和收藏表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsRelationService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商品点赞和收藏表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsRelation> list) {
        if (shopGoodsRelationService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商品点赞和收藏表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsRelation> batchParam) {
        if (batchParam.update(shopGoodsRelationService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商品点赞和收藏表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsRelationService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
