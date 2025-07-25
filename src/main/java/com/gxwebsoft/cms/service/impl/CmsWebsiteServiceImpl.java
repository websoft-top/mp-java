package com.gxwebsoft.cms.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.entity.*;
import com.gxwebsoft.cms.mapper.*;
import com.gxwebsoft.cms.param.*;
import com.gxwebsoft.cms.service.*;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.service.CompanyService;
import com.gxwebsoft.common.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站信息记录表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Service
public class CmsWebsiteServiceImpl extends ServiceImpl<CmsWebsiteMapper, CmsWebsite> implements CmsWebsiteService {
  @Resource
  private CmsWebsiteFieldMapper cmsWebsiteFieldMapper;
  @Resource
  private CmsModelMapper cmsModelMapper;
  @Resource
  private CmsNavigationMapper cmsNavigationMapper;
  @Resource
  private CmsLangLogMapper cmsLangLogMapper;
  @Resource
  private CmsAdMapper cmsAdMapper;
  @Resource
  private CmsLinkMapper cmsLinkMapper;
  @Resource
  private CmsArticleMapper cmsArticleMapper;
  @Resource
  private CmsWebsiteFieldService cmsWebsiteFieldService;
  @Resource
  private CmsModelService cmsModelService;
  @Resource
  private CmsNavigationService cmsNavigationService;
  @Resource
  private CmsLangLogService cmsLangLogService;
  @Resource
  private CmsAdService cmsAdService;
  @Resource
  private CmsLinkService cmsLinkService;
  @Resource
  private CmsArticleService cmsArticleService;
  @Resource
  private CmsArticleContentService cmsArticleContentService;
  @Resource
  private CmsWebsiteMapper cmsWebsiteMapper;
  @Resource
  private UserService userService;
  @Resource
  private CompanyService companyService;

  @Override
  public PageResult<CmsWebsite> pageRel(CmsWebsiteParam param) {
    PageParam<CmsWebsite, CmsWebsiteParam> page = new PageParam<>(param);
    page.setDefaultOrder("create_time desc");
    List<CmsWebsite> list = baseMapper.selectPageRel(page, param);
    list.forEach(d -> {
      // 即将过期(一周内过期的)
      d.setSoon(DateUtil.offsetDay(d.getExpirationTime(), -30).compareTo(DateUtil.date()));
      // 是否过期 -1已过期 大于0 未过期
      d.setStatus(d.getExpirationTime().compareTo(DateUtil.date()));
    });
    return new PageResult<>(list, page.getTotal());
  }

  @Override
  public List<CmsWebsite> listRel(CmsWebsiteParam param) {
    List<CmsWebsite> list = baseMapper.selectListRel(param);
    // 排序
    PageParam<CmsWebsite, CmsWebsiteParam> page = new PageParam<>();
    page.setDefaultOrder("create_time desc");
    return page.sortRecords(list);
  }

  @Override
  public CmsWebsite getByIdRel(Integer websiteId) {
    CmsWebsiteParam param = new CmsWebsiteParam();
    param.setWebsiteId(websiteId);
    return param.getOne(baseMapper.selectListRel(param));
  }

  @Override
  public PageResult<CmsWebsite> pageRelAll(CmsWebsiteParam param) {
    PageParam<CmsWebsite, CmsWebsiteParam> page = new PageParam<>(param);
    page.setDefaultOrder("create_time desc");
    List<CmsWebsite> list = baseMapper.selectPageRelAll(page, param);
    list.forEach(d -> {
      // 即将过期(一周内过期的)
      d.setSoon(DateUtil.offsetDay(d.getExpirationTime(), -30).compareTo(DateUtil.date()));
      // 是否过期 -1已过期 大于0 未过期
      d.setStatus(d.getExpirationTime().compareTo(DateUtil.date()));
    });
    return new PageResult<>(list, page.getTotal());
  }

  @Override
  public CmsWebsite create(CmsWebsite website) {
    final User loginUser = website.getLoginUser();
    // 创建站点
//    website.setWebsiteName("网站名称");
    website.setWebsiteCode("site-".concat(loginUser.getTenantId().toString()));
//    if (StrUtil.isBlank(website.getWebsiteLogo())) {
//      website.setWebsiteLogo("https://oss.wsdns.cn/20240822/0252ad4ed46449cdafe12f8d3d96c2ea.svg");
//    }
    website.setWebsiteIcon("/favicon.ico");
    website.setWebsiteType("云·企业官网");
    website.setAdminUrl("site.websoft.top");
    website.setVersion(10);
    website.setExpirationTime(DateUtil.nextMonth());
    website.setUserId(loginUser.getUserId());
    website.setDeveloper(loginUser.getNickname());
    website.setTenantId(loginUser.getTenantId());
    website.setTemplateId(loginUser.getTemplateId());
    website.setCompanyId(loginUser.getCompanyId());

    // 初始化数据
    if (save(website)) {
      // 插入网站设置记录
//      final CmsWebsiteSetting setting = new CmsWebsiteSetting();
//      setting.setWebsiteId(website.getWebsiteId());
//      setting.setCreateTime(DateUtil.date());
//      setting.setUpdateTime(DateUtil.date());
//      cmsWebsiteSettingService.save(setting);

      // 将网站创建者的userId做为查询条件 10257(4716),10324(6978),10398(26564)
      Integer websiteUserId = website.getTemplateId() != null ? website.getTemplateId() : 0;


      // TODO 国际化
      final CmsLangLogParam cmsLangLogParam = new CmsLangLogParam();
      cmsLangLogParam.setWebsiteUserId(websiteUserId);
      final List<CmsLangLog> logs = cmsLangLogMapper.selectListAllRel(cmsLangLogParam);
      logs.forEach(d -> {
        d.setTenantId(loginUser.getTenantId());
      });
      cmsLangLogService.saveBatch(logs);

      // TODO 复制参数
      final CmsWebsiteFieldParam param = new CmsWebsiteFieldParam();
      param.setUserId(websiteUserId);
      final List<CmsWebsiteField> fields = cmsWebsiteFieldMapper.selectListAllRel(param);
      fields.forEach(d -> {
        d.setTenantId(loginUser.getTenantId());
      });
      cmsWebsiteFieldService.saveBatch(fields);

      // TODO 复制模型
      final CmsModelParam modelParam = new CmsModelParam();
      modelParam.setWebsiteUserId(websiteUserId);
      final List<CmsModel> models = cmsModelMapper.selectListAllRel(modelParam);
      models.forEach(d -> {
        d.setUserId(loginUser.getUserId());
        d.setTenantId(loginUser.getTenantId());
      });
      cmsModelService.saveBatch(models);

      // TODO 复制广告
      final CmsAdParam cmsAdParam = new CmsAdParam();
      cmsAdParam.setWebsiteUserId(websiteUserId);
      final List<CmsAd> ads = cmsAdMapper.selectListAllRel(cmsAdParam);
      ads.forEach(d -> {
        d.setUserId(loginUser.getUserId());
        d.setTenantId(loginUser.getTenantId());
      });
      cmsAdService.saveBatch(ads);

      // TODO 复制链接
      CmsLinkParam cmsLinkParam = new CmsLinkParam();
      cmsLinkParam.setWebsiteUserId(websiteUserId);
      final List<CmsLink> links = cmsLinkMapper.selectListAllRel(cmsLinkParam);
      links.forEach(d -> {
        d.setUserId(loginUser.getUserId());
        d.setTenantId(loginUser.getTenantId());
      });
      cmsLinkService.saveBatch(links);

      // TODO 复制订单
//      CmsOrderParam cmsOrderParam = new CmsOrderParam();
//      cmsOrderParam.setWebsiteUserId(websiteUserId);
//      final List<CmsOrder> orders = cmsOrderMapper.selectListAllRel(cmsOrderParam);
//      orders.forEach(d -> {
//        d.setUserId(loginUser.getUserId());
//        d.setTenantId(loginUser.getTenantId());
//      });
//      cmsOrderService.saveBatch(orders);


      // TODO 复制栏目和文章、文章内容
      CmsNavigationParam cmsNavigationParam = new CmsNavigationParam();
      cmsNavigationParam.setWebsiteUserId(websiteUserId);
      cmsNavigationParam.setParentId(0);
      final List<CmsNavigation> parents = cmsNavigationMapper.selectListAllRel(cmsNavigationParam);
      parents.forEach(d -> {
        Integer navigationId = d.getNavigationId();
        // 复制顶级栏目
        d.setTenantId(loginUser.getTenantId());
        d.setUserId(loginUser.getUserId());
        if (cmsNavigationService.save(d)) {
          cmsNavigationService.saveAsync(d);
          // 复制栏目文章
          CmsArticleParam cmsArticleParam = new CmsArticleParam();
          cmsArticleParam.setWebsiteUserId(websiteUserId);
          cmsArticleParam.setCategoryId(navigationId);
          final List<CmsArticle> articles = cmsArticleMapper.selectListAllRel(cmsArticleParam);
          articles.forEach(a -> {
            a.setCategoryId(d.getNavigationId());
            a.setUserId(loginUser.getUserId());
            a.setTenantId(loginUser.getTenantId());
            if (cmsArticleService.save(a)) {
              final CmsArticleContent content = new CmsArticleContent();
              content.setArticleId(a.getArticleId());
              content.setContent(a.getContent());
              cmsArticleContentService.save(content);
            }
          });
          // 复制子栏目
          cmsNavigationParam.setParentId(navigationId);
          final List<CmsNavigation> navigations = cmsNavigationMapper.selectListAllRel(cmsNavigationParam);
          navigations.forEach(c -> {
            cmsArticleParam.setCategoryId(c.getNavigationId());
            System.out.println("c.getNavigationId() = " + c.getNavigationId());
            c.setParentId(d.getNavigationId());
            c.setTenantId(loginUser.getTenantId());
            c.setUserId(loginUser.getUserId());
            cmsNavigationService.save(c);
            cmsNavigationService.saveAsync(c);
            System.out.println("c2.getNavigationId() = " + c.getNavigationId());
            // 复制子栏目文章
            final List<CmsArticle> articles2 = cmsArticleMapper.selectListAllRel(cmsArticleParam);
            articles2.forEach(a2 -> {
              a2.setCategoryId(c.getNavigationId());
              a2.setParentId(c.getParentId());
              a2.setUserId(loginUser.getUserId());
              a2.setTenantId(loginUser.getTenantId());
              if (cmsArticleService.save(a2)) {
                final CmsArticleContent content = new CmsArticleContent();
                content.setArticleId(a2.getArticleId());
                content.setContent(a2.getContent());
                cmsArticleContentService.save(content);
              }
            });
          });
        }
      });
    }
    return website;
  }

  @Override
  public CmsWebsite getByIdRelAll(Integer id) {
    return cmsWebsiteMapper.getByIdRelAll(id);
  }

  @Override
  public boolean updateByIdAll(CmsWebsite cmsWebsite) {
    return baseMapper.updateByIdAll(cmsWebsite);
  }

  @Override
  public boolean removeByIdAll(Integer id) {
    return baseMapper.removeByIdAll(id);
  }

  @Override
  public CmsWebsite getByTenantId(Integer tenantId) {
    return baseMapper.getByTenantId(tenantId);
  }

}
