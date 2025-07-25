package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsMpFieldService;
import com.gxwebsoft.cms.entity.CmsMpField;
import com.gxwebsoft.cms.param.CmsMpFieldParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小程序配置控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "小程序配置管理")
@RestController
@RequestMapping("/api/cms/cms-mp-field")
public class CmsMpFieldController extends BaseController {
    @Resource
    private CmsMpFieldService cmsMpFieldService;

    @ApiOperation("分页查询小程序配置")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsMpField>> page(CmsMpFieldParam param) {
        // 使用关联查询
        return success(cmsMpFieldService.pageRel(param));
    }

    @ApiOperation("查询全部小程序配置")
    @GetMapping()
    public ApiResult<List<CmsMpField>> list(CmsMpFieldParam param) {
        PageParam<CmsMpField, CmsMpFieldParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsMpFieldService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsMpFieldService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsMpField:list')")
    @OperationLog
    @ApiOperation("根据id查询小程序配置")
    @GetMapping("/{id}")
    public ApiResult<CmsMpField> get(@PathVariable("id") Integer id) {
        return success(cmsMpFieldService.getById(id));
        // 使用关联查询
        //return success(cmsMpFieldService.getByIdRel(id));
    }

    @ApiOperation("添加小程序配置")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsMpField cmsMpField) {
        if (cmsMpFieldService.save(cmsMpField)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改小程序配置")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsMpField cmsMpField) {
        if (cmsMpFieldService.updateById(cmsMpField)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除小程序配置")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsMpFieldService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加小程序配置")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsMpField> list) {
        if (cmsMpFieldService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改小程序配置")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsMpField> batchParam) {
        if (batchParam.update(cmsMpFieldService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除小程序配置")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsMpFieldService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
