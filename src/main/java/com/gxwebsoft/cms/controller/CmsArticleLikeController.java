package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsArticleLikeService;
import com.gxwebsoft.cms.entity.CmsArticleLike;
import com.gxwebsoft.cms.param.CmsArticleLikeParam;
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
 * 点赞文章控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "点赞文章管理")
@RestController
@RequestMapping("/api/cms/cms-article-like")
public class CmsArticleLikeController extends BaseController {
    @Resource
    private CmsArticleLikeService cmsArticleLikeService;

    @ApiOperation("分页查询点赞文章")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsArticleLike>> page(CmsArticleLikeParam param) {
        // 使用关联查询
        return success(cmsArticleLikeService.pageRel(param));
    }

    @ApiOperation("查询全部点赞文章")
    @GetMapping()
    public ApiResult<List<CmsArticleLike>> list(CmsArticleLikeParam param) {
        PageParam<CmsArticleLike, CmsArticleLikeParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsArticleLikeService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsArticleLikeService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsArticleLike:list')")
    @OperationLog
    @ApiOperation("根据id查询点赞文章")
    @GetMapping("/{id}")
    public ApiResult<CmsArticleLike> get(@PathVariable("id") Integer id) {
        return success(cmsArticleLikeService.getById(id));
        // 使用关联查询
        //return success(cmsArticleLikeService.getByIdRel(id));
    }

    @ApiOperation("添加点赞文章")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsArticleLike cmsArticleLike) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsArticleLike.setUserId(loginUser.getUserId());
        }
        if (cmsArticleLikeService.save(cmsArticleLike)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改点赞文章")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsArticleLike cmsArticleLike) {
        if (cmsArticleLikeService.updateById(cmsArticleLike)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除点赞文章")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsArticleLikeService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加点赞文章")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsArticleLike> list) {
        if (cmsArticleLikeService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改点赞文章")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsArticleLike> batchParam) {
        if (batchParam.update(cmsArticleLikeService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除点赞文章")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsArticleLikeService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
