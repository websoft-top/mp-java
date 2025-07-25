package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsWebsiteFieldService;
import com.gxwebsoft.cms.entity.CmsWebsiteField;
import com.gxwebsoft.cms.param.CmsWebsiteFieldParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 应用参数控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Api(tags = "应用参数管理")
@RestController
@RequestMapping("/api/cms/cms-website-field")
public class CmsWebsiteFieldController extends BaseController {
    @Resource
    private CmsWebsiteFieldService cmsWebsiteFieldService;

    @ApiOperation("分页查询应用参数")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsWebsiteField>> page(CmsWebsiteFieldParam param) {
        // 使用关联查询
        return success(cmsWebsiteFieldService.pageRel(param));
    }

    @ApiOperation("查询全部应用参数")
    @GetMapping()
    public ApiResult<List<CmsWebsiteField>> list(CmsWebsiteFieldParam param) {
        // 使用关联查询
        return success(cmsWebsiteFieldService.listRel(param));
    }

    @ApiOperation("根据id查询应用参数")
    @GetMapping("/{id}")
    public ApiResult<CmsWebsiteField> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsWebsiteFieldService.getByIdRel(id));
    }

    @ApiOperation("添加应用参数")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsWebsiteField cmsWebsiteField) {
        if (cmsWebsiteFieldService.save(cmsWebsiteField)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改应用参数")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsWebsiteField cmsWebsiteField) {
        if (cmsWebsiteFieldService.updateById(cmsWebsiteField)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除应用参数")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsWebsiteFieldService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加应用参数")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsWebsiteField> list) {
        if (cmsWebsiteFieldService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改应用参数")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsWebsiteField> batchParam) {
        if (batchParam.update(cmsWebsiteFieldService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除应用参数")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsWebsiteFieldService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("获取网站配置参数-对象形式")
    @GetMapping("/config")
    public ApiResult<?> getConfig(CmsWebsiteFieldParam param) {
      // 使用关联查询
      final List<CmsWebsiteField> fields = cmsWebsiteFieldService.listRel(param);

      HashMap<String, Object> config = new HashMap<>();
      fields.forEach(d -> {
        config.put(d.getName(), d.getValue());
      });
      return success(config);
    }
}
