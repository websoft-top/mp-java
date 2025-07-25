package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsArticleCategoryService;
import com.gxwebsoft.cms.entity.CmsArticleCategory;
import com.gxwebsoft.cms.param.CmsArticleCategoryParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章分类表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "文章分类表管理")
@RestController
@RequestMapping("/api/cms/cms-article-category")
public class CmsArticleCategoryController extends BaseController {
    @Resource
    private CmsArticleCategoryService cmsArticleCategoryService;

    @ApiOperation("分页查询文章分类表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsArticleCategory>> page(CmsArticleCategoryParam param) {
        // 使用关联查询
        return success(cmsArticleCategoryService.pageRel(param));
    }

    @ApiOperation("查询全部文章分类表")
    @GetMapping()
    public ApiResult<List<CmsArticleCategory>> list(CmsArticleCategoryParam param) {
        // 使用关联查询
        return success(cmsArticleCategoryService.listRel(param));
    }

    @ApiOperation("根据id查询文章分类表")
    @GetMapping("/{id}")
    public ApiResult<CmsArticleCategory> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsArticleCategoryService.getByIdRel(id));
    }

    @ApiOperation("添加文章分类表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsArticleCategory cmsArticleCategory) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsArticleCategory.setUserId(loginUser.getUserId());
        }
        if (cmsArticleCategoryService.save(cmsArticleCategory)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改文章分类表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsArticleCategory cmsArticleCategory) {
        if (cmsArticleCategoryService.updateById(cmsArticleCategory)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除文章分类表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsArticleCategoryService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加文章分类表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsArticleCategory> list) {
        if (cmsArticleCategoryService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改文章分类表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsArticleCategory> batchParam) {
        if (batchParam.update(cmsArticleCategoryService, "category_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除文章分类表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsArticleCategoryService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
