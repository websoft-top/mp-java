package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsOrderService;
import com.gxwebsoft.cms.entity.CmsOrder;
import com.gxwebsoft.cms.param.CmsOrderParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单控制器
 *
 * @author 科技小王子
 * @since 2024-11-25 12:14:05
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/api/cms/cms-order")
public class CmsOrderController extends BaseController {
    @Resource
    private CmsOrderService cmsOrderService;

    @PreAuthorize("hasAuthority('cms:cmsOrder:list')")
    @ApiOperation("分页查询订单")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsOrder>> page(CmsOrderParam param) {
        // 使用关联查询
        return success(cmsOrderService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsOrder:list')")
    @ApiOperation("查询全部订单")
    @GetMapping()
    public ApiResult<List<CmsOrder>> list(CmsOrderParam param) {
        // 使用关联查询
        return success(cmsOrderService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsOrder:list')")
    @ApiOperation("根据id查询订单")
    @GetMapping("/{id}")
    public ApiResult<CmsOrder> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsOrderService.getByIdRel(id));
    }

    @ApiOperation("添加订单")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsOrder cmsOrder) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsOrder.setUserId(loginUser.getUserId());
        }
        if(cmsOrder.getCode() == null){
          return fail("验证码不正确",null);
        }
        if(cmsOrder.getOrderNo() == null){
          cmsOrder.setOrderNo(CommonUtil.createOrderNo());
        }
        // 默认语言
        if(cmsOrder.getLang() == null){
          cmsOrder.setLang("zh_CN");
        }
        if (cmsOrderService.save(cmsOrder)) {
            return success("提交成功");
        }
        return fail("提交失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsOrder:update')")
    @ApiOperation("修改订单")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsOrder cmsOrder) {
        if (cmsOrderService.updateById(cmsOrder)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsOrder:remove')")
    @ApiOperation("删除订单")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsOrderService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsOrder:save')")
    @ApiOperation("批量添加订单")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsOrder> list) {
        if (cmsOrderService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsOrder:update')")
    @ApiOperation("批量修改订单")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsOrder> batchParam) {
        if (batchParam.update(cmsOrderService, "order_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsOrder:remove')")
    @ApiOperation("批量删除订单")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsOrderService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
