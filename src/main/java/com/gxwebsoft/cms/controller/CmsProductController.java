package com.gxwebsoft.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.cms.param.CmsProductSpecParam;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsProductService;
import com.gxwebsoft.cms.entity.CmsProduct;
import com.gxwebsoft.cms.param.CmsProductParam;
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
 * 产品控制器
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Api(tags = "产品管理")
@RestController
@RequestMapping("/api/cms/cms-product")
public class CmsProductController extends BaseController {
    @Resource
    private CmsProductService cmsProductService;

    @ApiOperation("分页查询产品")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsProduct>> page(CmsProductParam param) {
        // 使用关联查询
        return success(cmsProductService.pageRel(param));
    }

    @ApiOperation("查询全部产品")
    @GetMapping()
    public ApiResult<List<CmsProduct>> list(CmsProductParam param) {
        // 使用关联查询
        return success(cmsProductService.listRel(param));
    }

    @ApiOperation("根据id查询产品")
    @GetMapping("/{id}")
    public ApiResult<CmsProduct> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsProductService.getByIdRel(id));
    }
    @PreAuthorize("hasAuthority('cms:cmsProduct:save')")
    @OperationLog
    @ApiOperation("添加产品")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsProduct cmsProduct) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsProduct.setUserId(loginUser.getUserId());
        }
        if (cmsProductService.save(cmsProduct)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }
    @PreAuthorize("hasAuthority('cms:cmsProduct:update')")
    @OperationLog
    @ApiOperation("修改产品")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsProduct cmsProduct) {
        if (cmsProductService.updateById(cmsProduct)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }
    @PreAuthorize("hasAuthority('cms:cmsProduct:remove')")
    @OperationLog
    @ApiOperation("删除产品")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsProductService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }
    @PreAuthorize("hasAuthority('cms:cmsProduct:save')")
    @OperationLog
    @ApiOperation("批量添加产品")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsProduct> list) {
        if (cmsProductService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }
    @PreAuthorize("hasAuthority('cms:cmsProduct:update')")
    @OperationLog
    @ApiOperation("批量修改产品")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsProduct> batchParam) {
        if (batchParam.update(cmsProductService, "product_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }
    @PreAuthorize("hasAuthority('cms:cmsProduct:remove')")
    @OperationLog
    @ApiOperation("批量删除产品")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsProductService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("统计信息")
    @GetMapping("/data")
    public ApiResult<Map<String, Integer>> data(CmsProductSpecParam param) {
        Map<String, Integer> data = new HashMap<>();
        final LambdaQueryWrapper<CmsProduct> wrapper = new LambdaQueryWrapper<>();

        if(param.getMerchantId() != null){
            wrapper.eq(CmsProduct::getMerchantId,param.getMerchantId());
        }

        Integer totalNum = cmsProductService.count(
                wrapper.eq(CmsProduct::getDeleted,0).eq(CmsProduct::getStatus,0)
        );
        data.put("totalNum", totalNum);

        Integer totalNum2 = cmsProductService.count(
                wrapper.eq(CmsProduct::getStatus,1)
        );
        data.put("totalNum2", totalNum2);

        Integer totalNum3 = cmsProductService.count(
                wrapper.gt(CmsProduct::getStatus,1)
        );
        data.put("totalNum3", totalNum3);

        return success(data);
    }
}
