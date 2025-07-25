package com.gxwebsoft.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Dict;
import com.gxwebsoft.common.system.entity.DictData;
import com.gxwebsoft.common.system.param.DictDataParam;
import com.gxwebsoft.common.system.param.DictParam;
import com.gxwebsoft.common.system.service.DictDataService;
import com.gxwebsoft.common.system.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典控制器
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:03
 */
@Api(tags = "字典管理(业务类)")
@RestController
@RequestMapping("/api/system/dict")
public class DictController extends BaseController {
    @Resource
    private DictService dictService;
    @Resource
    private DictDataService dictDataService;

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @ApiOperation("分页查询字典")
    @GetMapping("/page")
    public ApiResult<PageResult<Dict>> page(DictParam param) {
        PageParam<Dict, DictParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(dictService.page(page, page.getWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @ApiOperation("查询全部字典")
    @GetMapping()
    public ApiResult<List<Dict>> list(DictParam param) {
        PageParam<Dict, DictParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(dictService.list(page.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @ApiOperation("查询全部字典")
    @GetMapping("/tree")
    public ApiResult<?> tree() {
      final HashMap<Object, Object> result = new HashMap<>();
      final List<DictData> dictData = dictDataService.listRel(new DictDataParam());
      final Map<String, List<DictData>> dataCollect = dictData.stream().collect(Collectors.groupingBy(DictData::getDictCode));
      for (String code : dataCollect.keySet()) {
        Dict dict = new Dict();
        dict.setDictCode(code);
        final Set<Set<String>> list = new LinkedHashSet<>();
        Set<String> codes = new LinkedHashSet<>();
        for(DictData item : dictData){
          if (item.getDictCode().equals(code)) {
            codes.add(item.getDictDataCode());
          }
        }
        list.add(codes);
        dict.setItems(list);
        result.put(code,dict.getItems());
      }
      return success(result);
    }

    @PreAuthorize("hasAuthority('sys:dict:list')")
    @ApiOperation("根据id查询字典")
    @GetMapping("/{id}")
    public ApiResult<Dict> get(@PathVariable("id") Integer id) {
        return success(dictService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @ApiOperation("添加字典")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Dict dict) {
        if (dictService.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getDictCode, dict.getDictCode())) > 0) {
            return fail("字典标识已存在");
        }
        if (dictService.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getDictName, dict.getDictName())) > 0) {
            return fail("字典名称已存在");
        }
        if (dictService.save(dict)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:update')")
    @ApiOperation("修改字典")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Dict dict) {
        if (dictService.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getDictCode, dict.getDictCode())
                .ne(Dict::getDictId, dict.getDictId())) > 0) {
            return fail("字典标识已存在");
        }
        if (dictService.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getDictName, dict.getDictName())
                .ne(Dict::getDictId, dict.getDictId())) > 0) {
            return fail("字典名称已存在");
        }
        if (dictService.updateById(dict)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @ApiOperation("删除字典")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (dictService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:dict:save')")
    @ApiOperation("批量添加字典")
    @PostMapping("/batch")
    public ApiResult<List<String>> saveBatch(@RequestBody List<Dict> list) {
        if (CommonUtil.checkRepeat(list, Dict::getDictCode)) {
            return fail("字典标识不能重复", null);
        }
        if (CommonUtil.checkRepeat(list, Dict::getDictName)) {
            return fail("字典名称不能重复", null);
        }
        List<Dict> codeExists = dictService.list(new LambdaQueryWrapper<Dict>()
                .in(Dict::getDictCode, list.stream().map(Dict::getDictCode)
                        .collect(Collectors.toList())));
        if (codeExists.size() > 0) {
            return fail("字典标识已存在", codeExists.stream().map(Dict::getDictCode)
                    .collect(Collectors.toList())).setCode(2);
        }
        List<Dict> nameExists = dictService.list(new LambdaQueryWrapper<Dict>()
                .in(Dict::getDictName, list.stream().map(Dict::getDictCode)
                        .collect(Collectors.toList())));
        if (nameExists.size() > 0) {
            return fail("字典名称已存在", nameExists.stream().map(Dict::getDictName)
                    .collect(Collectors.toList())).setCode(3);
        }
        if (dictService.saveBatch(list)) {
            return success("添加成功", null);
        }
        return fail("添加失败", null);
    }

    @PreAuthorize("hasAuthority('sys:dict:remove')")
    @ApiOperation("批量删除字典")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (dictService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
