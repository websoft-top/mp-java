package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsMpAdService;
import com.gxwebsoft.cms.entity.CmsMpAd;
import com.gxwebsoft.cms.param.CmsMpAdParam;
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
 * 小程序广告位控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "小程序广告位管理")
@RestController
@RequestMapping("/api/cms/cms-mp-ad")
public class CmsMpAdController extends BaseController {
    @Resource
    private CmsMpAdService cmsMpAdService;

    @ApiOperation("分页查询小程序广告位")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsMpAd>> page(CmsMpAdParam param) {
        // 使用关联查询
        return success(cmsMpAdService.pageRel(param));
    }

    @ApiOperation("查询全部小程序广告位")
    @GetMapping()
    public ApiResult<List<CmsMpAd>> list(CmsMpAdParam param) {
        PageParam<CmsMpAd, CmsMpAdParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsMpAdService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsMpAdService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsMpAd:list')")
    @OperationLog
    @ApiOperation("根据id查询小程序广告位")
    @GetMapping("/{id}")
    public ApiResult<CmsMpAd> get(@PathVariable("id") Integer id) {
        return success(cmsMpAdService.getById(id));
        // 使用关联查询
        //return success(cmsMpAdService.getByIdRel(id));
    }

    @ApiOperation("添加小程序广告位")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsMpAd cmsMpAd) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsMpAd.setUserId(loginUser.getUserId());
        }
        if (cmsMpAdService.save(cmsMpAd)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改小程序广告位")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsMpAd cmsMpAd) {
        if (cmsMpAdService.updateById(cmsMpAd)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除小程序广告位")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsMpAdService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加小程序广告位")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsMpAd> list) {
        if (cmsMpAdService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改小程序广告位")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsMpAd> batchParam) {
        if (batchParam.update(cmsMpAdService, "ad_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除小程序广告位")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsMpAdService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
