package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsArticleContentService;
import com.gxwebsoft.cms.entity.CmsArticleContent;
import com.gxwebsoft.cms.param.CmsArticleContentParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章记录表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "文章记录表管理")
@RestController
@RequestMapping("/api/cms/cms-article-content")
public class CmsArticleContentController extends BaseController {
    @Resource
    private CmsArticleContentService cmsArticleContentService;

    @ApiOperation("分页查询文章记录表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsArticleContent>> page(CmsArticleContentParam param) {
        // 使用关联查询
        return success(cmsArticleContentService.pageRel(param));
    }

    @ApiOperation("查询全部文章记录表")
    @GetMapping()
    public ApiResult<List<CmsArticleContent>> list(CmsArticleContentParam param) {
//        PageParam<CmsArticleContent, CmsArticleContentParam> page = new PageParam<>(param);
//        page.setDefaultOrder("create_time desc");
//        return success(cmsArticleContentService.list(page.getOrderWrapper()));
        // 使用关联查询
        return success(cmsArticleContentService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsArticleContent:list')")
    @OperationLog
    @ApiOperation("根据id查询文章记录表")
    @GetMapping("/{id}")
    public ApiResult<CmsArticleContent> get(@PathVariable("id") Integer id) {
//        return success(cmsArticleContentService.getById(id));
        // 使用关联查询
        return success(cmsArticleContentService.getByIdRel(id));
    }

    @ApiOperation("添加文章记录表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsArticleContent cmsArticleContent) {
        if (cmsArticleContentService.save(cmsArticleContent)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改文章记录表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsArticleContent cmsArticleContent) {
        if (cmsArticleContentService.updateById(cmsArticleContent)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除文章记录表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsArticleContentService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加文章记录表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsArticleContent> list) {
        if (cmsArticleContentService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改文章记录表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsArticleContent> batchParam) {
        if (batchParam.update(cmsArticleContentService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除文章记录表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsArticleContentService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
