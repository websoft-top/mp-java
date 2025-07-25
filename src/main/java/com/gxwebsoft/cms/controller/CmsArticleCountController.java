package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsArticleCountService;
import com.gxwebsoft.cms.entity.CmsArticleCount;
import com.gxwebsoft.cms.param.CmsArticleCountParam;
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
@RequestMapping("/api/cms/cms-article-count")
public class CmsArticleCountController extends BaseController {
    @Resource
    private CmsArticleCountService cmsArticleCountService;

    @ApiOperation("分页查询点赞文章")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsArticleCount>> page(CmsArticleCountParam param) {
        // 使用关联查询
        return success(cmsArticleCountService.pageRel(param));
    }

    @ApiOperation("查询全部点赞文章")
    @GetMapping()
    public ApiResult<List<CmsArticleCount>> list(CmsArticleCountParam param) {
        PageParam<CmsArticleCount, CmsArticleCountParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsArticleCountService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsArticleCountService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsArticleCount:list')")
    @OperationLog
    @ApiOperation("根据id查询点赞文章")
    @GetMapping("/{id}")
    public ApiResult<CmsArticleCount> get(@PathVariable("id") Integer id) {
        return success(cmsArticleCountService.getById(id));
        // 使用关联查询
        //return success(cmsArticleCountService.getByIdRel(id));
    }

    @ApiOperation("添加点赞文章")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsArticleCount cmsArticleCount) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsArticleCount.setUserId(loginUser.getUserId());
        }
        if (cmsArticleCountService.save(cmsArticleCount)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改点赞文章")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsArticleCount cmsArticleCount) {
        if (cmsArticleCountService.updateById(cmsArticleCount)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除点赞文章")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsArticleCountService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加点赞文章")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsArticleCount> list) {
        if (cmsArticleCountService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改点赞文章")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsArticleCount> batchParam) {
        if (batchParam.update(cmsArticleCountService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除点赞文章")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsArticleCountService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
