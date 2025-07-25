package com.gxwebsoft.common.system.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.*;
import com.gxwebsoft.common.system.entity.*;
import com.gxwebsoft.common.system.mapper.CompanyMapper;
import com.gxwebsoft.common.system.mapper.TenantMapper;
import com.gxwebsoft.common.system.param.CompanyParam;
import com.gxwebsoft.common.system.service.*;
import com.gxwebsoft.shop.entity.ShopMerchantApply;
import com.gxwebsoft.shop.service.ShopMerchantApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 企业信息控制器
 *
 * @author 科技小王子
 * @since 2023-05-27 14:57:34
 */
@Api(tags = "企业")
@RestController
@RequestMapping("/api/system/company")
public class CompanyController extends BaseController {
    @Resource
    private CompanyService companyService;
    @Resource
    private ShopMerchantApplyService shopMerchantApplyService;
    @Resource
    private CompanyContentService companyContentService;
    @Resource
    private CompanyUrlService companyUrlService;
    @Resource
    private CompanyParameterService companyParameterService;
    @Resource
    private TenantService tenantService;
    @Resource
    private CompanyMapper companyMapper;
    @Resource
    private TenantMapper tenantMapper;
    @Resource
    private DomainService domainService;
    @Resource
    private UserCollectionService userCollectionService;
    @Resource
    private RedisUtil redisUtil;


    @ApiOperation("分页查询企业信息不限租户")
    @GetMapping("/pageAll")
    public ApiResult<PageResult<Company>> pageAll(CompanyParam param) {
      final PageResult<Company> result = companyService.pageRelAll(param);
      result.getList().forEach(d -> {
        d.setPhone(DesensitizedUtil.mobilePhone(d.getPhone()));
        d.setCompanyCode(DesensitizedUtil.idCardNum(d.getCompanyCode(),1,2));
      });
      final User loginUser = getLoginUser();
      if(loginUser != null){
        // 我的收藏
        final List<UserCollection> myFocus = userCollectionService.list(new LambdaQueryWrapper<UserCollection>().eq(UserCollection::getUserId, getLoginUserId()));
        if (!CollectionUtils.isEmpty(myFocus)) {
          final Set<Integer> collect = myFocus.stream().map(UserCollection::getTid).collect(Collectors.toSet());
          if (param.getVersion() != null) {
            // 我的收藏
            if (param.getVersion().equals(99)) {
              param.setVersion(null);
              param.setCompanyIds(collect);
            }
          }
          result.getList().forEach(d -> {
            d.setCollection(collect.contains(d.getCompanyId()));
          });
          return success(result);
        }
      }
      // 使用关联查询
      return success(result);
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @ApiOperation("分页查询企业信息")
    @GetMapping("/page")
    public ApiResult<PageResult<Company>> page(CompanyParam param) {
        // 使用关联查询
        return success(companyService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("查询全部企业信息")
    @GetMapping()
    public ApiResult<List<Company>> list(CompanyParam param) {
        // 使用关联查询
        return success(companyService.listRel(param));
    }

    @ApiOperation("根据id查询企业信息")
    @GetMapping("/{id}")
    public ApiResult<Company> get(@PathVariable("id") Integer id) {
        // 使用关联查询
      final Company company = companyService.getByIdRel(id);
      if (ObjectUtil.isNotEmpty(company)) {
        // 应用详情
        final CompanyContent content = companyContentService.getOne(new LambdaQueryWrapper<CompanyContent>().eq(CompanyContent::getCompanyId, company.getCompanyId()).last("limit 1"));
        if (ObjectUtil.isNotEmpty(content)) {
          company.setContent(content.getContent());
        }
        // 应用链接
        company.setLinks(companyUrlService.list(new LambdaQueryWrapper<CompanyUrl>().eq(CompanyUrl::getCompanyId, company.getCompanyId())));
        // 应用参数
        company.setParameters(companyParameterService.list(new LambdaQueryWrapper<CompanyParameter>().eq(CompanyParameter::getCompanyId, company.getCompanyId())));

      }
      return success(company);
    }

    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
    @PreAuthorize("hasAuthority('sys:company:save')")
    @ApiOperation("添加企业信息")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Company company) {
        Tenant tenant = new Tenant();
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          company.setUserId(loginUser.getUserId());
          tenant.setUserId(loginUser.getUserId());
        }
        tenant.setTenantName(company.getShortName());
        tenant.setTenantCode(CommonUtil.randomUUID16());
        tenant.setComments(company.getComments());
        tenantService.save(tenant);
        company.setTenantId(tenant.getTenantId());
        company.setTid(tenant.getTenantId());
        company.setAuthoritative(true);
        // 添加租户并初始化
//        final Company result = tenantService.initialization(company);
//        if (result != null) {
//          return success("添加成功",result);
//        }
        return fail("添加失败");
    }

  @PreAuthorize("hasAuthority('sys:company:update')")
  @OperationLog
  @ApiOperation("修改企业信息")
  @PutMapping()
  public ApiResult<?> update(@RequestBody Company company) {
    // 授权新的免费域名
    if (StrUtil.isNotBlank(company.getFreeDomain())) {
      // 待授权的二级域名
      String domain = company.getFreeDomain().concat(".websoft.top");
      // 删除旧授权域名
      final Domain one = domainService.getOne(new LambdaQueryWrapper<Domain>().eq(Domain::getType, 2).eq(Domain::getCompanyId, company.getCompanyId()).eq(Domain::getDeleted,0).last("limit 1"));
      if(one != null){
        redisUtil.delete("Domain:".concat(one.getDomain()));
        domainService.removeById(one);
      }
      // 保存记录
      final Domain sysDomain = new Domain();
      sysDomain.setDomain(domain);
      sysDomain.setType(2);
      sysDomain.setSortNumber(100);
      sysDomain.setCompanyId(company.getCompanyId());
      sysDomain.setTenantId(company.getTenantId());
      domainService.save(sysDomain);
      company.setDomain(domain);
      // 写入缓存
      redisUtil.set("Domain:".concat(domain), company.getTenantId());
    }
    // 同步更新租户表
    if(StrUtil.isNotBlank(company.getShortName())){
      final Tenant tenant = new Tenant();
      tenant.setTenantId(company.getTenantId());
      tenant.setTenantName(company.getShortName());
      tenantService.updateById(tenant);
    }
    if (companyService.updateById(company)) {
      // 清除缓存
      redisUtil.delete("TenantInfo:".concat(company.getTenantId().toString()));
      return success("修改成功");
    }
    return fail("修改失败");
  }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("删除企业信息")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
      final Company company = companyService.getById(id);
      tenantService.removeById(company.getTenantId());
      if (companyService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("批量添加企业信息")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Company> list) {
        if (companyService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("批量修改企业信息")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<Company> batchParam) {
        if (batchParam.update(companyService, "company_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("批量删除企业信息")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (companyService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("根据id查询企业信息")
    @GetMapping("/profile")
    public ApiResult<Company> profile() {
      final User loginUser = getLoginUser();
      if(loginUser != null){
        final Company company = companyService.getOne(new LambdaQueryWrapper<Company>().eq(Company::getTenantId, loginUser.getTenantId()).eq(Company::getAuthoritative, true).last("limit 1"));
        if (ObjectUtil.isNotEmpty(company)) {
          final ShopMerchantApply apply = shopMerchantApplyService.getOne(new LambdaQueryWrapper<ShopMerchantApply>().eq(ShopMerchantApply::getTenantId, loginUser.getTenantId()).last("limit 1"));
          if (ObjectUtil.isNotEmpty(apply)) {
            company.setCompanyName(apply.getMerchantName());
            company.setCompanyType(apply.getShopType());
            if (apply.getStatus().equals(1)) {
              company.setAuthentication(1);
            }
          }
          // 即将过期(一周内过期的)
          company.setSoon(DateUtil.offsetDay(company.getExpirationTime(), -7).compareTo(DateUtil.date()));
          // 是否过期 -1已过期 大于0 未过期
          company.setStatus(company.getExpirationTime().compareTo(DateUtil.date()));
          return success(company);
        }
      }
      return fail("企业不存在",null);
    }

    @PreAuthorize("hasAuthority('sys:company:profile')")
    @OperationLog
    @ApiOperation("根据id查询企业信息不限租户")
    @GetMapping("/profileAll/{companyId}")
    public ApiResult<Company> profileAll(@PathVariable("companyId") Integer companyId) {
      return success(companyMapper.getCompanyAll(companyId));
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("销毁租户")
    @DeleteMapping("/destruction/{id}")
    public ApiResult<?> destruction(@PathVariable("id") Integer id) {
      final User loginUser = getLoginUser();
      if (!loginUser.getUsername().equals("admin")) {
        throw new BusinessException("只有超级管理员才能操作");
      }
      final Integer tenantId = getTenantId();
      if (tenantService.removeById(tenantId)) {
        return success("删除成功",tenantId);
      }
      return fail("删除失败");
    }
  @ApiOperation("检查企业是否存在")
  @GetMapping("/existence")
  public ApiResult<?> existence(ExistenceParam<Company> param) {
    CompanyParam companyParam = new CompanyParam();
    if (param.getField().equals("shortName")) {
      companyParam.setAppName(param.getValue());
      List<Company> count = companyMapper.getCount(companyParam);
      if (!CollectionUtils.isEmpty(count)) {
        return success(param.getValue() + "已存在");
      }
    }
    if (param.getField().equals("email")) {
      companyParam.setEmail(param.getValue());
      List<Company> count = companyMapper.getCount(companyParam);
      if (!CollectionUtils.isEmpty(count)) {
        return success(param.getValue() + "已存在");
      }
    }
    if (param.getField().equals("phone")) {
      companyParam.setPhone(param.getValue());
      List<Company> count = companyMapper.getCount(companyParam);
      if (!CollectionUtils.isEmpty(count)) {
        return success(param.getValue() + "已存在");
      }
    }
    return fail(param.getValue() + "不存在");
  }

  @ApiOperation("根据id查询企业信息不限租户不带token")
  @GetMapping("/companyInfoAll/{companyId}")
  public ApiResult<Company> companyInfoAll(@PathVariable("companyId") Integer companyId) {
    return success(companyMapper.getCompanyAll(companyId));
  }

  @PreAuthorize("hasAuthority('sys:company:updateAll')")
  @OperationLog
  @ApiOperation("修改企业信息")
  @PutMapping("/updateCompanyAll")
  public ApiResult<?> updateCompanyAll(@RequestBody Company company) {
    if (companyMapper.updateByIdAll(company)) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @PreAuthorize("hasAuthority('sys:company:removeAll')")
  @OperationLog
  @ApiOperation("删除企业信息")
  @DeleteMapping("/removeAll/{id}")
  public ApiResult<?> removeAll(@PathVariable("id") Integer id) {
    if (companyMapper.removeCompanyAll(id)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @PreAuthorize("hasAuthority('sys:company:removeAll')")
  @OperationLog
  @ApiOperation("恢复租户")
  @DeleteMapping("/undeleteAll/{id}")
  public ApiResult<?> undeleteAll(@PathVariable("id") Integer id) {
    if (companyMapper.undeleteAll(id)) {
      return success("恢复成功");
    }
    return fail("恢复失败");
  }

}
