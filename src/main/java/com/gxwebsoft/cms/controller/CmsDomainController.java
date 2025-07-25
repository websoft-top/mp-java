package com.gxwebsoft.cms.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.cms.mapper.CmsDomainMapper;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsDomainService;
import com.gxwebsoft.cms.entity.CmsDomain;
import com.gxwebsoft.cms.param.CmsDomainParam;
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
 * 网站域名记录表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Api(tags = "网站域名记录表管理")
@RestController
@RequestMapping("/api/cms/cms-domain")
public class CmsDomainController extends BaseController {
    @Resource
    private CmsDomainService cmsDomainService;
    @Resource
    private CmsDomainMapper cmsDomainMapper;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation("分页查询网站域名记录表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsDomain>> page(CmsDomainParam param) {
        // 使用关联查询
        return success(cmsDomainService.pageRel(param));
    }

    @ApiOperation("查询全部网站域名记录表")
    @GetMapping()
    public ApiResult<List<CmsDomain>> list(CmsDomainParam param) {
        PageParam<CmsDomain, CmsDomainParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsDomainService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsDomainService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsDomain:list')")
    @OperationLog
    @ApiOperation("根据id查询网站域名记录表")
    @GetMapping("/{id}")
    public ApiResult<CmsDomain> get(@PathVariable("id") Integer id) {
        return success(cmsDomainService.getById(id));
        // 使用关联查询
        //return success(cmsDomainService.getByIdRel(id));
    }

    @ApiOperation("添加网站域名记录表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsDomain cmsDomain) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsDomain.setUserId(loginUser.getUserId());
        }
        if (cmsDomainService.save(cmsDomain)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改网站域名记录表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsDomain cmsDomain) {
        if (cmsDomainService.updateById(cmsDomain)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除网站域名记录表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsDomainService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加网站域名记录表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsDomain> list) {
        if (cmsDomainService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改网站域名记录表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsDomain> batchParam) {
        if (batchParam.update(cmsDomainService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除网站域名记录表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsDomainService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("查询授权域名信息")
    @GetMapping("/getTenantIdByDomain")
    public ApiResult<?> getTenantIdByDomain(CmsDomainParam param) {
      final CmsDomain domain = cmsDomainService.getOne(new LambdaQueryWrapper<CmsDomain>().eq(CmsDomain::getDomain, param.getDomain()).last("limit 1"));
      return success(domain);
    }

    @ApiOperation("授权二级域名")
    @PostMapping("/domain")
    public ApiResult<?> domain(@RequestBody CmsDomain cmsDomain) {
      final User loginUser = getLoginUser();
      String key = "Domain:" + cmsDomain.getDomain();
      final Integer tenantId = loginUser.getTenantId();
      final CmsDomain domain = cmsDomainService.getOne(new LambdaQueryWrapper<CmsDomain>()
        .eq(CmsDomain::getWebsiteId, cmsDomain.getWebsiteId()).last("limit 1"));
      if (ObjectUtil.isNotEmpty(domain)) {
        // 重写缓存
        redisUtil.set(key,tenantId);
        domain.setDomain(cmsDomain.getDomain());
        cmsDomainService.updateById(domain);
        return success("授权成功");
      }
      if(ObjectUtil.isEmpty(domain)){
        cmsDomain.setUserId(loginUser.getUserId());
        cmsDomain.setSortNumber(100);
        cmsDomain.setStatus(1);
        cmsDomain.setHostName("@");
        cmsDomain.setWebsiteId(cmsDomain.getWebsiteId());
        cmsDomain.setTenantId(tenantId);
        if(cmsDomainService.save(cmsDomain)){
          // 重写缓存
          redisUtil.set(key,tenantId);
          return success("授权成功");
        }
      }
      return fail("授权失败");
    }

}
