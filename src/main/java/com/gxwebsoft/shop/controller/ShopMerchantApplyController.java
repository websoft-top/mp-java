package com.gxwebsoft.shop.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.entity.ShopMerchant;
import com.gxwebsoft.shop.entity.ShopMerchantAccount;
import com.gxwebsoft.shop.service.ShopMerchantApplyService;
import com.gxwebsoft.shop.entity.ShopMerchantApply;
import com.gxwebsoft.shop.param.ShopMerchantApplyParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.shop.service.ShopMerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商户入驻申请控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商户入驻申请管理")
@RestController
@RequestMapping("/api/shop/shop-merchant-apply")
public class ShopMerchantApplyController extends BaseController {
  @Resource
  private ShopMerchantApplyService shopMerchantApplyService;
  @Resource
  private ShopMerchantService shopMerchantService;

  @ApiOperation("分页查询商户入驻申请")
  @GetMapping("/page")
  public ApiResult<PageResult<ShopMerchantApply>> page(ShopMerchantApplyParam param) {
    // 使用关联查询
    return success(shopMerchantApplyService.pageRel(param));
  }

  @ApiOperation("查询全部商户入驻申请")
  @GetMapping()
  public ApiResult<List<ShopMerchantApply>> list(ShopMerchantApplyParam param) {
    // 使用关联查询
    return success(shopMerchantApplyService.listRel(param));
  }

  @PreAuthorize("hasAuthority('shop:shopMerchantApply:list')")
  @ApiOperation("根据id查询商户入驻申请")
  @GetMapping("/{id}")
  public ApiResult<ShopMerchantApply> get(@PathVariable("id") Integer id) {
    // 使用关联查询
    return success(shopMerchantApplyService.getByIdRel(id));
  }

  @ApiOperation("添加商户入驻申请")
  @PostMapping()
  public ApiResult<?> save(@RequestBody ShopMerchantApply shopMerchantApply) {
    // 记录当前登录用户id
    User loginUser = getLoginUser();
    if (loginUser != null) {
      shopMerchantApply.setUserId(loginUser.getUserId());
      if(shopMerchantApplyService.count(new LambdaQueryWrapper<ShopMerchantApply>().eq(ShopMerchantApply::getPhone,shopMerchantApply.getPhone())) > 0){
        return fail("该手机号码已存在");
      }
      // 个人开发者认证材料：使用姓名+身份证号码
      if (shopMerchantApply.getType().equals(0)) {
        shopMerchantApply.setMerchantName(shopMerchantApply.getRealName());
        shopMerchantApply.setMerchantCode(shopMerchantApply.getIdCard());
      }
      shopMerchantApply.setCheckStatus(true);
      shopMerchantApply.setTenantId(loginUser.getTenantId());
      if (shopMerchantApplyService.save(shopMerchantApply)) {
        return success("您的申请已提交，请耐心等待工作人员的审核，非常感谢");
      }
    }
    return fail("提交失败");
  }

  @ApiOperation("修改商户入驻申请")
  @PutMapping()
  public ApiResult<?> update(@RequestBody ShopMerchantApply shopMerchantApply) {
    if (shopMerchantApplyService.updateById(shopMerchantApply)) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @ApiOperation("删除商户入驻申请")
  @DeleteMapping("/{id}")
  public ApiResult<?> remove(@PathVariable("id") Integer id) {
    if (shopMerchantApplyService.removeById(id)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("批量添加商户入驻申请")
  @PostMapping("/batch")
  public ApiResult<?> saveBatch(@RequestBody List<ShopMerchantApply> list) {
    if (shopMerchantApplyService.saveBatch(list)) {
      return success("添加成功");
    }
    return fail("添加失败");
  }

  @ApiOperation("批量修改商户入驻申请")
  @PutMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopMerchantApply> batchParam) {
    if (batchParam.update(shopMerchantApplyService, "apply_id")) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @ApiOperation("批量删除商户入驻申请")
  @DeleteMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
    if (shopMerchantApplyService.removeByIds(ids)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("我的入驻信息")
  @GetMapping("/getByUserId")
  public ApiResult<ShopMerchantApply> getByUserId() {
    final User loginUser = getLoginUser();
    if (loginUser == null) {
      return fail("请先登录", null);
    }
    final ShopMerchantApply shopMerchantApply = shopMerchantApplyService.getOne(new LambdaQueryWrapper<ShopMerchantApply>().eq(ShopMerchantApply::getUserId, getLoginUser().getUserId()).last("limit 1"));
    return success(shopMerchantApply);
  }

  @PreAuthorize("hasAuthority('shop:shopMerchantApply:update')")
  @ApiOperation("入驻审核")
  @PutMapping("/check")
  public ApiResult<?> check(@RequestBody ShopMerchantApply shopMerchantApply) {
    // 审核中?
    shopMerchantApply.setCheckStatus(true);
    // TODO 审核通过则创建商户
    if (shopMerchantApply.getStatus().equals(1)) {
      final ShopMerchant one = shopMerchantService.getOne(new LambdaQueryWrapper<ShopMerchant>().eq(ShopMerchant::getPhone, shopMerchantApply.getPhone()).last("limit 1"));
      final ShopMerchantAccount merchantAccount = new ShopMerchantAccount();
      BeanUtils.copyProperties(shopMerchantApply, merchantAccount);

      final User user = new User();

      if (ObjectUtil.isNotEmpty(one)) {
        BeanUtils.copyProperties(shopMerchantApply, one);
        one.setStatus(0);
        shopMerchantService.updateById(one);
        user.setRealName(shopMerchantApply.getRealName());
      } else {
        final ShopMerchant merchant = new ShopMerchant();
        BeanUtils.copyProperties(shopMerchantApply, merchant);
        merchant.setStatus(0);
        shopMerchantService.save(merchant);
        user.setRealName(shopMerchantApply.getRealName());
      }

      // TODO 创建商户账号
      // TODO 更新用户表的商户信息
      user.setUserId(shopMerchantApply.getUserId());
      shopMerchantApplyService.updateById(shopMerchantApply);
      // TODO 入驻开发者中心(添加会员)
      return success("操作成功");
    }
    // TODO 驳回
    if (shopMerchantApply.getStatus().equals(2)) {
      shopMerchantApply.setCheckStatus(false);
      shopMerchantApplyService.updateById(shopMerchantApply);
      return success("操作成功");
    }
    // 审核状态
    shopMerchantApply.setStatus(0);
    if (shopMerchantApplyService.updateById(shopMerchantApply)) {
      return success("您的申请已提交，请耐心等待工作人员的审核，非常感谢");
    }
    return fail("操作失败");
  }
}
