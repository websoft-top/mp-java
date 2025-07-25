package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsMpMenuService;
import com.gxwebsoft.cms.entity.CmsMpMenu;
import com.gxwebsoft.cms.param.CmsMpMenuParam;
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
 * 小程序端菜单控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "小程序端菜单管理")
@RestController
@RequestMapping("/api/cms/cms-mp-menu")
public class CmsMpMenuController extends BaseController {
    @Resource
    private CmsMpMenuService cmsMpMenuService;

    @ApiOperation("分页查询小程序端菜单")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsMpMenu>> page(CmsMpMenuParam param) {
        // 使用关联查询
        return success(cmsMpMenuService.pageRel(param));
    }

    @ApiOperation("查询全部小程序端菜单")
    @GetMapping()
    public ApiResult<List<CmsMpMenu>> list(CmsMpMenuParam param) {
        PageParam<CmsMpMenu, CmsMpMenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsMpMenuService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsMpMenuService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsMpMenu:list')")
    @OperationLog
    @ApiOperation("根据id查询小程序端菜单")
    @GetMapping("/{id}")
    public ApiResult<CmsMpMenu> get(@PathVariable("id") Integer id) {
        return success(cmsMpMenuService.getById(id));
        // 使用关联查询
        //return success(cmsMpMenuService.getByIdRel(id));
    }

    @ApiOperation("添加小程序端菜单")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsMpMenu cmsMpMenu) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsMpMenu.setUserId(loginUser.getUserId());
        }
        if (cmsMpMenuService.save(cmsMpMenu)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改小程序端菜单")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsMpMenu cmsMpMenu) {
        if (cmsMpMenuService.updateById(cmsMpMenu)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除小程序端菜单")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsMpMenuService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加小程序端菜单")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsMpMenu> list) {
        if (cmsMpMenuService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改小程序端菜单")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsMpMenu> batchParam) {
        if (batchParam.update(cmsMpMenuService, "menu_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除小程序端菜单")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsMpMenuService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
