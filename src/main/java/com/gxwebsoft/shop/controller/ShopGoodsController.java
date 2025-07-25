package com.gxwebsoft.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsService;
import com.gxwebsoft.shop.entity.ShopGoods;
import com.gxwebsoft.shop.param.ShopGoodsParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品控制器
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/shop/shop-goods")
public class ShopGoodsController extends BaseController {
    @Resource
    private ShopGoodsService shopGoodsService;

    @ApiOperation("分页查询商品")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoods>> page(ShopGoodsParam param) {
        // 使用关联查询
        return success(shopGoodsService.pageRel(param));
    }

    @ApiOperation("查询全部商品")
    @GetMapping()
    public ApiResult<List<ShopGoods>> list(ShopGoodsParam param) {
        // 使用关联查询
        return success(shopGoodsService.listRel(param));
    }

    @ApiOperation("根据id查询商品")
    @GetMapping("/{id}")
    public ApiResult<ShopGoods> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopGoods:save')")
    @OperationLog
    @ApiOperation("添加商品")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoods shopGoods) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopGoods.setUserId(loginUser.getUserId());
        }
        if (shopGoodsService.save(shopGoods)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoods:update')")
    @OperationLog
    @ApiOperation("修改商品")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoods shopGoods) {
        if (shopGoodsService.updateById(shopGoods)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoods:remove')")
    @OperationLog
    @ApiOperation("删除商品")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoods:save')")
    @OperationLog
    @ApiOperation("批量添加商品")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoods> list) {
        if (shopGoodsService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoods:update')")
    @OperationLog
    @ApiOperation("批量修改商品")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoods> batchParam) {
        if (batchParam.update(shopGoodsService, "goods_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoods:remove')")
    @OperationLog
    @ApiOperation("批量删除商品")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

  @ApiOperation("统计信息")
  @GetMapping("/data")
  public ApiResult<Map<String, Integer>> data(ShopGoodsParam param) {
    Map<String, Integer> data = new HashMap<>();
    final LambdaQueryWrapper<ShopGoods> wrapper = new LambdaQueryWrapper<>();

    if (param.getMerchantId() != null) {
      wrapper.eq(ShopGoods::getMerchantId,param.getMerchantId());
    }

    Integer totalNum = shopGoodsService.count(
      wrapper.eq(ShopGoods::getStatus,0).gt(ShopGoods::getStock,0)
    );
    data.put("totalNum", totalNum);
    wrapper.clear();

    Integer totalNum2 = shopGoodsService.count(
      wrapper.gt(ShopGoods::getStatus,0)
    );
    data.put("totalNum2", totalNum2);
    wrapper.clear();

    Integer totalNum3 = shopGoodsService.count(
      wrapper.eq(ShopGoods::getStock,0)
    );
    data.put("totalNum3", totalNum3);
    wrapper.clear();

    // 下架已售罄的商品
    shopGoodsService.update(new LambdaUpdateWrapper<ShopGoods>().eq(ShopGoods::getStock,0).set(ShopGoods::getStatus,1));

    return success(data);
  }

}
