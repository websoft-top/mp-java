package com.gxwebsoft.cms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.entity.CmsDesign;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.mapper.CmsNavigationMapper;
import com.gxwebsoft.cms.service.CmsDesignService;
import com.gxwebsoft.cms.service.CmsModelService;
import com.gxwebsoft.cms.service.CmsNavigationService;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.param.CmsNavigationParam;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

/**
 * 网站导航记录表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsNavigationServiceImpl extends ServiceImpl<CmsNavigationMapper, CmsNavigation> implements CmsNavigationService {
  @Resource
  private CmsDesignService cmsDesignService;
  @Resource
  private CmsModelService cmsModelService;
  @Resource
  private CmsNavigationService cmsNavigationService;
  @Resource
  private UserService userService;

  @Override
    public PageResult<CmsNavigation> pageRel(CmsNavigationParam param) {
        PageParam<CmsNavigation, CmsNavigationParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, position asc, navigation_id asc");
        List<CmsNavigation> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsNavigation> listRel(CmsNavigationParam param) {
        List<CmsNavigation> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsNavigation, CmsNavigationParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, position asc,navigation_id asc");
        return page.sortRecords(list);
    }

    @Override
    public CmsNavigation getByIdRel(Integer navigationId) {
        CmsNavigationParam param = new CmsNavigationParam();
        param.setNavigationId(navigationId);
        CmsNavigation navigation;
        navigation = param.getOne(baseMapper.selectListRel(param));
        if (ObjectUtil.isEmpty(navigation)) {
          return null;
        }
        // 父级栏目并且是page模型则读取子项目第一条
        if (navigation.getParentId().equals(0) && navigation.getModel().equals("page")) {
          final CmsNavigation parent = cmsNavigationService.getOne(new LambdaQueryWrapper<CmsNavigation>().eq(CmsNavigation::getParentId, navigation.getNavigationId()).last("limit 1"));
          if (ObjectUtil.isNotEmpty(parent)) {
            navigation = parent;
          }
        }
        // 所属页面
        navigation.setDesign(cmsDesignService.getOne(new LambdaQueryWrapper<CmsDesign>().eq(CmsDesign::getCategoryId, navigation.getNavigationId()).last("limit 1")));
        // 所属模型
        if (StrUtil.isNotBlank(navigation.getModel())) {
          navigation.setModelInfo(cmsModelService.getOne(new LambdaQueryWrapper<CmsModel>().eq(CmsModel::getModel, navigation.getModel()).last("limit 1")));
          if (StrUtil.isBlank(navigation.getBanner())) {
            navigation.setBanner(navigation.getModelInfo().getBanner());
            navigation.setMpBanner(navigation.getModelInfo().getThumb());
          }
        }
      return navigation;
    }

    /**
     * 配置路由生成规则
     * path：/模型/导航ID
     * component: /pages/模型/index.vue
     */
    @Override
    public void saveAsync(CmsNavigation navigation) {
      // TODO 1.设计path和component生产规则
      final String path = navigation.getPath();
      final CmsModel model = cmsModelService.getOne(new LambdaQueryWrapper<CmsModel>().eq(CmsModel::getModel, navigation.getModel()).last("limit 1"));
      // 1.自动配置
      navigation.setPath("/" + navigation.getModel() + "/" + navigation.getNavigationId());
      navigation.setTarget("_self");
      navigation.setComponent(MessageFormat.format("/pages/{0}/{1}", navigation.getModel(), "[id].vue"));

      // 1.2自定义文件后缀
      if(!navigation.getModel().equals("index") && model.getSuffix() != null){
        navigation.setPath(navigation.getPath() + model.getSuffix());
      }

      // 2.特例：默认首页
      if (navigation.getPath().equals("/") || navigation.getModel().equals("index")) {
      final int count = count(new LambdaQueryWrapper<CmsNavigation>().eq(CmsNavigation::getPath, "/").eq(CmsNavigation::getLang,navigation.getLang()));
        if(count > 1){
          throw new BusinessException("路由地址已存在！");
        }
        navigation.setPath("/");
        navigation.setComponent("/pages/index.vue");
        navigation.setModel("index");
        navigation.setHome(1);
      }
      // 3.外链模型
      if (navigation.getModel().equals("links")) {
        navigation.setPath(path);
        navigation.setTarget("_blank");
        navigation.setComponent(null);
      }

      // 4.密码可见
      if(StrUtil.isNotBlank(navigation.getPassword())){
        navigation.setPassword(userService.encodePassword(navigation.getPassword()));
      }

      // 更新操作
      updateById(navigation);

      // TODO 2.同步添加页面
      final CmsDesign one = cmsDesignService.getOne(new LambdaQueryWrapper<CmsDesign>().eq(CmsDesign::getCategoryId, navigation.getNavigationId()).eq(CmsDesign::getDeleted, 0).last("limit 1"));
      if (ObjectUtil.isEmpty(one)) {
        final CmsDesign design = new CmsDesign();
        design.setName(navigation.getTitle());
        design.setCategoryId(navigation.getNavigationId());
        design.setKeywords(navigation.getTitle());
        design.setDescription(navigation.getComments());
        design.setPath(navigation.getPath());
        design.setComponent(navigation.getComponent());
        design.setTenantId(navigation.getTenantId());
        if (StrUtil.isNotBlank(navigation.getContent())) {
          design.setContent(navigation.getContent());
        }
        cmsDesignService.save(design);
      }

      // 面包屑
//      final CmsNavigation parent = getById(navigation.getParentId());
//      if (ObjectUtil.isNotEmpty(parent) && navigation.getParentId() > 0) {
//        navigation.setParentName(parent.getTitle());
//        navigation.setParentPath(parent.getPath());
//        navigation.setParentId(parent.getNavigationId());
//        updateById(parent);
//      }
    }

}
