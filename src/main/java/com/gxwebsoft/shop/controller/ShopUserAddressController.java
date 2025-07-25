    package com.gxwebsoft.shop.controller;

    import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
    import com.gxwebsoft.common.core.web.BaseController;
    import com.gxwebsoft.shop.service.ShopUserAddressService;
    import com.gxwebsoft.shop.entity.ShopUserAddress;
    import com.gxwebsoft.shop.param.ShopUserAddressParam;
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
    import java.util.List;

    /**
     * 收货地址控制器
     *
     * @author 科技小王子
     * @since 2025-07-22 23:06:40
     */
    @Api(tags = "收货地址管理")
    @RestController
    @RequestMapping("/api/shop/shop-user-address")
    public class ShopUserAddressController extends BaseController {
        @Resource
        private ShopUserAddressService shopUserAddressService;

        @PreAuthorize("hasAuthority('shop:shopUserAddress:list')")
        @ApiOperation("分页查询收货地址")
        @GetMapping("/page")
        public ApiResult<PageResult<ShopUserAddress>> page(ShopUserAddressParam param) {
            // 使用关联查询
            return success(shopUserAddressService.pageRel(param));
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:list')")
        @ApiOperation("查询全部收货地址")
        @GetMapping()
        public ApiResult<List<ShopUserAddress>> list(ShopUserAddressParam param) {
            // 使用关联查询
            return success(shopUserAddressService.listRel(param));
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:list')")
        @ApiOperation("根据id查询收货地址")
        @GetMapping("/{id}")
        public ApiResult<ShopUserAddress> get(@PathVariable("id") Integer id) {
            // 使用关联查询
            return success(shopUserAddressService.getByIdRel(id));
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:save')")
        @OperationLog
        @ApiOperation("添加收货地址")
        @PostMapping()
        public ApiResult<?> save(@RequestBody ShopUserAddress shopUserAddress) {
            // 记录当前登录用户id
            User loginUser = getLoginUser();
            if (loginUser != null) {
             shopUserAddress.setUserId(loginUser.getUserId());
              if (shopUserAddressService.count(new LambdaQueryWrapper<ShopUserAddress>().eq(ShopUserAddress::getUserId, loginUser.getUserId()).eq(ShopUserAddress::getAddress, shopUserAddress.getAddress())) > 0) {
                return success("该地址已存在");
              }
            }
            if (shopUserAddressService.save(shopUserAddress)) {
                return success("添加成功");
            }
            return fail("添加失败");
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:update')")
        @OperationLog
        @ApiOperation("修改收货地址")
        @PutMapping()
        public ApiResult<?> update(@RequestBody ShopUserAddress shopUserAddress) {
            if (shopUserAddressService.updateById(shopUserAddress)) {
                return success("修改成功");
            }
            return fail("修改失败");
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:remove')")
        @OperationLog
        @ApiOperation("删除收货地址")
        @DeleteMapping("/{id}")
        public ApiResult<?> remove(@PathVariable("id") Integer id) {
            if (shopUserAddressService.removeById(id)) {
                return success("删除成功");
            }
            return fail("删除失败");
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:save')")
        @OperationLog
        @ApiOperation("批量添加收货地址")
        @PostMapping("/batch")
        public ApiResult<?> saveBatch(@RequestBody List<ShopUserAddress> list) {
            if (shopUserAddressService.saveBatch(list)) {
                return success("添加成功");
            }
            return fail("添加失败");
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:update')")
        @OperationLog
        @ApiOperation("批量修改收货地址")
        @PutMapping("/batch")
        public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopUserAddress> batchParam) {
            if (batchParam.update(shopUserAddressService, "id")) {
                return success("修改成功");
            }
            return fail("修改失败");
        }

        @PreAuthorize("hasAuthority('shop:shopUserAddress:remove')")
        @OperationLog
        @ApiOperation("批量删除收货地址")
        @DeleteMapping("/batch")
        public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
            if (shopUserAddressService.removeByIds(ids)) {
                return success("删除成功");
            }
            return fail("删除失败");
        }

    }
