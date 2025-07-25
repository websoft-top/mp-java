package com.gxwebsoft.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.DictData;
import com.gxwebsoft.common.system.param.DictDataParam;
import com.gxwebsoft.common.system.service.DictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据控制器
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:04
 */
@Api(tags = "字典数据管理(业务类)")
@RestController
@RequestMapping("/api/system/dict-data")
public class DictDataController extends BaseController {
    @Resource
    private DictDataService dictDataService;

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @ApiOperation("分页查询字典数据")
    @GetMapping("/page")
    public ApiResult<PageResult<DictData>> page(DictDataParam param) {
        return success(dictDataService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @ApiOperation("查询全部字典数据")
    @GetMapping()
    public ApiResult<List<DictData>> list(DictDataParam param) {
        return success(dictDataService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @ApiOperation("根据id查询字典数据")
    @GetMapping("/{id}")
    public ApiResult<DictData> get(@PathVariable("id") Integer id) {
        return success(dictDataService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @ApiOperation("添加字典数据")
    @PostMapping()
    public ApiResult<?> add(@RequestBody DictData dictData) {
        if (dictDataService.count(new LambdaQueryWrapper<DictData>()
                .eq(DictData::getDictId, dictData.getDictId())
                .eq(DictData::getDictDataName, dictData.getDictDataName())) > 0) {
            return fail("字典数据名称已存在");
        }
        if (dictDataService.count(new LambdaQueryWrapper<DictData>()
                .eq(DictData::getDictId, dictData.getDictId())
                .eq(DictData::getDictDataCode, dictData.getDictDataCode())) > 0) {
            return fail("字典数据标识已存在");
        }
        if (dictDataService.save(dictData)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:update')")
    @ApiOperation("修改字典数据")
    @PutMapping()
    public ApiResult<?> update(@RequestBody DictData dictData) {
        if (dictDataService.count(new LambdaQueryWrapper<DictData>()
                .eq(DictData::getDictId, dictData.getDictId())
                .eq(DictData::getDictDataName, dictData.getDictDataName())
                .ne(DictData::getDictDataId, dictData.getDictDataId())) > 0) {
            return fail("字典数据名称已存在");
        }
        if (dictDataService.count(new LambdaQueryWrapper<DictData>()
                .eq(DictData::getDictId, dictData.getDictId())
                .eq(DictData::getDictDataCode, dictData.getDictDataCode())
                .ne(DictData::getDictDataId, dictData.getDictDataId())) > 0) {
            return fail("字典数据标识已存在");
        }
        if (dictDataService.updateById(dictData)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @ApiOperation("删除字典数据")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (dictDataService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @ApiOperation("批量添加字典数据")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<DictData> dictDataList) {
        if (dictDataService.saveBatch(dictDataList)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @ApiOperation("批量删除字典数据")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (dictDataService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
