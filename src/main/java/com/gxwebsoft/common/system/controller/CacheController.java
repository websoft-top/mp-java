package com.gxwebsoft.common.system.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.core.utils.CacheClient;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.system.entity.Cache;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存控制器
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
@Api(tags = "缓存管理")
@RestController
@RequestMapping("/api/system/cache")
public class CacheController extends BaseController {
    @Resource
    private SettingService settingService;
    @Resource
    private CacheClient cacheClient;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PreAuthorize("hasAuthority('sys:cache:list')")
    @ApiOperation("查询全部缓存")
    @GetMapping()
    public ApiResult<HashMap<String, Object>> list() {
      String key = "cache".concat(getTenantId().toString()).concat("*");
      final Set<String> keys = stringRedisTemplate.keys(key);
      final HashMap<String, Object> map = new HashMap<>();
      final ArrayList<Object> list = new ArrayList<>();
      assert keys != null;
      keys.forEach(d -> {
        final Cache cache = new Cache();
        cache.setKey(d);
        try {
          final String content = stringRedisTemplate.opsForValue().get(d);
          if(content != null){
            cache.setContent(stringRedisTemplate.opsForValue().get(d));
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        list.add(cache);
      });
      map.put("count",keys.size());
      map.put("list",list);
      return success(map);
    }

    @PreAuthorize("hasAuthority('sys:cache:list')")
    @ApiOperation("根据key查询缓存信息")
    @GetMapping("/{key}")
    public ApiResult<?> get(@PathVariable("key") String key) {
      final String s = redisUtil.get(key + getTenantId());
      if(StrUtil.isNotBlank(s)){
        return success("读取成功", JSONObject.parseObject(s));
      }
      return fail("缓存不存在！");
    }

    @PreAuthorize("hasAuthority('sys:cache:save')")
    @ApiOperation("添加缓存")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Cache cache) {
      if (cache.getExpireTime() != null) {
        redisUtil.set(cache.getKey() + ":" + getTenantId(),cache.getContent(),cache.getExpireTime(), TimeUnit.MINUTES);
        return success("缓存成功");
      }
      redisUtil.set(cache.getKey() + ":" + getTenantId(),cache.getContent());
      return success("缓存成功");
    }

    @PreAuthorize("hasAuthority('sys:cache:save')")
    @ApiOperation("删除缓存")
    @DeleteMapping("/{key}")
    public ApiResult<?> remove(@PathVariable("key") String key) {
      if (Boolean.TRUE.equals(stringRedisTemplate.delete(key))) {
        return success("删除成功");
      }
      return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:cache:save')")
    @ApiOperation("缓存皮肤")
    @PostMapping("/theme")
    public ApiResult<?> saveTheme(@RequestBody Cache cache) {
      final User loginUser = getLoginUser();
      final String username = loginUser.getUsername();
      if (username.equals("admin")) {
        redisUtil.set(cache.getKey() + ":" + getTenantId(),cache.getContent());
        return success("缓存成功");
      }
      return success("缓存失败");
    }


}
