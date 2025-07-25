package com.gxwebsoft.common.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.entity.CmsWebsite;
import com.gxwebsoft.cms.param.CmsWebsiteParam;
import com.gxwebsoft.cms.service.CmsWebsiteService;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Company;
import com.gxwebsoft.common.system.entity.Role;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.entity.UserRole;
import com.gxwebsoft.common.system.mapper.UserMapper;
import com.gxwebsoft.common.system.param.CompanyParam;
import com.gxwebsoft.common.system.param.UserParam;
import com.gxwebsoft.common.system.service.CompanyService;
import com.gxwebsoft.common.system.service.RoleMenuService;
import com.gxwebsoft.common.system.service.UserRoleService;
import com.gxwebsoft.common.system.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户Service实现
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Resource
  private UserRoleService userRoleService;
  @Resource
  private RoleMenuService roleMenuService;
  @Resource
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Resource
  private CmsWebsiteService cmsWebsiteService;
  @Resource
  private CompanyService companyService;
  @Resource
  private RedisUtil redisUtil;

  @Override
  public PageResult<User> pageRel(UserParam param) {
    PageParam<User, UserParam> page = new PageParam<>(param);
    page.setDefaultOrder("create_time desc");
    List<User> list = baseMapper.selectPageRel(page, param);
    // 查询用户的角色
    selectUserRoles(list);
    return new PageResult<>(list, page.getTotal());
  }

  @Override
  public List<User> listRel(UserParam param) {
    List<User> list = baseMapper.selectListRel(param);
    // 查询用户的角色
    selectUserRoles(list);
    // 排序
    PageParam<User, UserParam> page = new PageParam<>(param);
    page.setDefaultOrder("create_time desc");
    return page.sortRecords(list);
  }

  @Override
  public User getByIdRel(Integer userId) {
    UserParam param = new UserParam();
    param.setUserId(userId);
    User user = param.getOne(baseMapper.selectListRel(param));
    if (user != null) {
      user.setRoles(userRoleService.listByUserId(user.getUserId()));
      user.setAuthorities(roleMenuService.listMenuByUserId(user.getUserId(), null));
      // 系统配置信息
//      Map<String, Object> map = new HashMap<>();
      // 1)云存储
//      String key = "setting:upload:" + user.getTenantId();
//      final String upload = redisUtil.get(key);
//      if(upload != null){
//        final JSONObject object = JSONObject.parseObject(upload);
//        map.put("uploadMethod",object.getString("uploadMethod"));
//        map.put("bucketDomain",object.getString("bucketDomain"));
//        map.put("fileUrl",object.getString("fileUrl") + "/");
//        user.setSystem(map);
//      }
    }
    return user;
  }

  @Override
  public User getByUsername(String username) {
    return getByUsername(username, null);
  }

  @Override
  public User getByUsername(String username, Integer tenantId) {
    if (StrUtil.isBlank(username)) {
      return null;
    }
    User user = baseMapper.selectByUsername(username, tenantId);
    if (user != null) {
      user.setRoles(userRoleService.listByUserId(user.getUserId()));
      user.setAuthorities(roleMenuService.listMenuByUserId(user.getUserId(), null));
    }
    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getByUsername(username);
  }

  @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.SERIALIZABLE)
  @Override
  public boolean saveUser(User user) {
    if (StrUtil.isNotEmpty(user.getUsername()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
      .eq(User::getUsername, user.getUsername())) > 0) {
      throw new BusinessException("账号已存在");
    }
    if (StrUtil.isNotEmpty(user.getPhone()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
      .eq(User::getPhone, user.getPhone())) > 0) {
      throw new BusinessException("手机号已存在");
    }
    if (StrUtil.isNotEmpty(user.getEmail()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
      .eq(User::getEmail, user.getEmail())) > 0) {
      throw new BusinessException("邮箱已存在");
    }
    boolean result = baseMapper.insert(user) > 0;
    if (result && user.getRoles() != null && user.getRoles().size() > 0) {
      List<Integer> roleIds = user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toList());
      if (userRoleService.saveBatch(user.getUserId(), roleIds) < roleIds.size()) {
        throw new BusinessException("用户角色添加失败");
      }
    }
    return result;
  }

  @Transactional(rollbackFor = {Exception.class})
  @Override
  public boolean updateUser(User user) {
    if (StrUtil.isNotEmpty(user.getUsername()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
      .eq(User::getUsername, user.getUsername())
      .ne(User::getUserId, user.getUserId())) > 0) {
      throw new BusinessException("账号已存在");
    }
    if (StrUtil.isNotEmpty(user.getPhone()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
      .eq(User::getPhone, user.getPhone())
      .ne(User::getUserId, user.getUserId())) > 0) {
      throw new BusinessException("手机号已存在");
    }
    if (StrUtil.isNotEmpty(user.getEmail()) && baseMapper.selectCount(new LambdaQueryWrapper<User>()
      .eq(User::getEmail, user.getEmail())
      .ne(User::getUserId, user.getUserId())) > 0) {
      throw new BusinessException("邮箱已存在");
    }
    boolean result = baseMapper.updateById(user) > 0;
    if (result && user.getRoles() != null && user.getRoles().size() > 0) {
      userRoleService.remove(new LambdaUpdateWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
      List<Integer> roleIds = user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toList());
      if (userRoleService.saveBatch(user.getUserId(), roleIds) < roleIds.size()) {
        throw new BusinessException("用户角色添加失败");
      }
    }
    return result;
  }

  @Override
  public boolean comparePassword(String dbPassword, String inputPassword) {
    return bCryptPasswordEncoder.matches(inputPassword, dbPassword);
  }

  @Override
  public String encodePassword(String password) {
    return password == null ? null : bCryptPasswordEncoder.encode(password);
  }

  @Override
  public User getByPhone(String phone) {
    return query().eq("phone", phone).one();
  }

  @Override
  public User getByUnionId(UserParam param) {
    return param.getOne(baseMapper.getOne(param));
  }

  @Override
  public User getByOauthId(UserParam userParam) {
    return userParam.getOne(baseMapper.getOne(userParam));
  }

  @Override
  public List<User> listStatisticsRel(UserParam param) {
    List<User> list = baseMapper.selectListStatisticsRel(param);
    return list;
  }

  /**
   * 更新用户信息(跨租户)
   *
   * @param user 用户信息
   */
  @Override
  public void updateByUserId(User user) {
    baseMapper.updateByUserId(user);
  }

  @Override
  public List<User> pageAdminByPhone(UserParam param) {
    return baseMapper.pageAdminByPhone(param);
  }

  @Override
  public List<User> listByAlert() {
    return baseMapper.listByAlert();
  }

  /**
   * 批量查询用户的角色
   *
   * @param users 用户集合
   */
  private void selectUserRoles(List<User> users) {
    if (users != null && users.size() > 0) {
      List<Integer> userIds = users.stream().map(User::getUserId).collect(Collectors.toList());
      List<Role> userRoles = userRoleService.listByUserIds(userIds);
      for (User user : users) {
        List<Role> roles = userRoles.stream().filter(d -> user.getUserId().equals(d.getUserId()))
          .collect(Collectors.toList());
        user.setRoles(roles);
      }
    }
  }
}
