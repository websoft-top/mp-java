package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsArticleCommentService;
import com.gxwebsoft.cms.entity.CmsArticleComment;
import com.gxwebsoft.cms.param.CmsArticleCommentParam;
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
 * 文章评论表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "文章评论表管理")
@RestController
@RequestMapping("/api/cms/cms-article-comment")
public class CmsArticleCommentController extends BaseController {
    @Resource
    private CmsArticleCommentService cmsArticleCommentService;

    @ApiOperation("分页查询文章评论表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsArticleComment>> page(CmsArticleCommentParam param) {
        // 使用关联查询
        return success(cmsArticleCommentService.pageRel(param));
    }

    @ApiOperation("查询全部文章评论表")
    @GetMapping()
    public ApiResult<List<CmsArticleComment>> list(CmsArticleCommentParam param) {
        PageParam<CmsArticleComment, CmsArticleCommentParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsArticleCommentService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsArticleCommentService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsArticleComment:list')")
    @OperationLog
    @ApiOperation("根据id查询文章评论表")
    @GetMapping("/{id}")
    public ApiResult<CmsArticleComment> get(@PathVariable("id") Integer id) {
        return success(cmsArticleCommentService.getById(id));
        // 使用关联查询
        //return success(cmsArticleCommentService.getByIdRel(id));
    }

    @ApiOperation("添加文章评论表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsArticleComment cmsArticleComment) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsArticleComment.setUserId(loginUser.getUserId());
        }
        if (cmsArticleCommentService.save(cmsArticleComment)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改文章评论表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsArticleComment cmsArticleComment) {
        if (cmsArticleCommentService.updateById(cmsArticleComment)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除文章评论表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsArticleCommentService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加文章评论表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsArticleComment> list) {
        if (cmsArticleCommentService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改文章评论表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsArticleComment> batchParam) {
        if (batchParam.update(cmsArticleCommentService, "comment_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除文章评论表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsArticleCommentService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
