package com.gxwebsoft.common.core.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.result.RedisResult;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.gxwebsoft.common.core.constants.RedisConstants.CACHE_NULL_TTL;

@Component
public class CacheClient {
  private final StringRedisTemplate stringRedisTemplate;
  public static Integer tenantId;

  public CacheClient(StringRedisTemplate stringRedisTemplate){
    this.stringRedisTemplate = stringRedisTemplate;
  }

  /**
   * 写入redis缓存
   * @param key [表名]:id
   * @param entity 实体类对象
   * 示例 cacheClient.set("merchant:"+id,merchant)
   */
  public <T> void set(String key, T entity){
    stringRedisTemplate.opsForValue().set(prefix(key), JSONUtil.toJSONString(entity));
  }

  /**
   * 写入redis缓存
   * @param key [表名]:id
   * @param entity 实体类对象
   * 示例 cacheClient.set("merchant:"+id,merchant,1L,TimeUnit.DAYS)
   */
  public <T> void set(String key, T entity, Long time, TimeUnit unit){
    stringRedisTemplate.opsForValue().set(prefix(key), JSONUtil.toJSONString(entity),time,unit);
  }

  /**
   * 读取redis缓存
   * @param key [表名]:id
   * 示例 cacheClient.get(key)
   * @return merchant
   */
  public String get(String key) {
      return stringRedisTemplate.opsForValue().get(prefix(key));
  }

  /**
   * 读取redis缓存
   * @param key [表名]:id
   * @param clazz Merchant.class
   * @param <T>
   * 示例 cacheClient.get("merchant:"+id,Merchant.class)
   * @return merchant
   */
  public <T> T get(String key, Class<T> clazz) {
    String json = stringRedisTemplate.opsForValue().get(prefix(key));
    if(StrUtil.isNotBlank(json)){
      return JSONUtil.parseObject(json, clazz);
    }
    return null;
  }

  /**
   * 写redis缓存(哈希类型)
   * @param key [表名]:id
   * @param field 字段
   * 示例 cacheClient.get("merchant:"+id,Merchant.class)
   */
  public <T> void hPut(String key, String field, T entity) {
    stringRedisTemplate.opsForHash().put(prefix(key),field,JSONUtil.toJSONString(entity));
  }

  /**
   * 写redis缓存(哈希类型)
   * @param key [表名]:id
   * @param map 字段
   * 示例 cacheClient.get("merchant:"+id,Merchant.class)
   */
  public void hPutAll(String key, Map<String,String> map) {
    stringRedisTemplate.opsForHash().putAll(prefix(key),map);
  }

  /**
   * 读取redis缓存(哈希类型)
   * 示例 cacheClient.get("merchant:"+id,Merchant.class)
   * @param key [表名]:id
   * @param field 字段
   * @return merchant
   */
  public <T> T hGet(String key, String field, Class<T> clazz) {
    Object obj = stringRedisTemplate.opsForHash().get(prefix(key), field);
    return JSONUtil.parseObject(JSONUtil.toJSONString(obj),clazz);
  }

  public List<Object> hValues(String key){
    return stringRedisTemplate.opsForHash().values(prefix(key));
  }

  public Long hSize(String key){
    return stringRedisTemplate.opsForHash().size(prefix(key));
  }

  // 逻辑过期方式写入redis
  public <T> void setWithLogicalExpire(String key, T value, Long time, TimeUnit unit){
    // 设置逻辑过期时间
    final RedisResult<T> redisResult = new RedisResult<>();
    redisResult.setData(value);
    redisResult.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
    stringRedisTemplate.opsForValue().set(prefix(key),JSONUtil.toJSONString(redisResult));
  }

  // 读取redis
  public <R,ID> R query(String keyPrefix, ID id, Class<R> clazz, Function<ID,R> dbFallback, Long time, TimeUnit unit){
    String key = keyPrefix + id;
    // 1.从redis查询缓存
    final String json = stringRedisTemplate.opsForValue().get(prefix(key));
    // 2.判断是否存在
    if (StrUtil.isNotBlank(json)) {
      // 3.存在，直接返回
      return JSONUtil.parseObject(json,clazz);
    }
    // 判断命中的是否为空值
    if (json != null) {
      return null;
    }
    // 4. 不存在，跟进ID查询数据库
    R r = dbFallback.apply(id);
    // 5. 数据库不存在，返回错误
    if(r == null){
      // 空值写入数据库
      this.set(prefix(key),"",CACHE_NULL_TTL,TimeUnit.MINUTES);
      return null;
    }
    // 写入redis
    this.set(prefix(key),r,time,unit);
    return r;
  }

  /**
   * 添加商户定位点
   * @param key geo
   * @param id
   * 示例 cacheClient.geoAdd("merchant-geo",merchant)
   */
  public <T> void geoAdd(String key, Double x, Double y, String id){
      stringRedisTemplate.opsForGeo().add(prefix(key),new Point(x,y),id);
  }

  /**
   * 删除定位
   * @param key geo
   * @param id
   * 示例 cacheClient.geoRemove("merchant-geo",id)
   */
  public void geoRemove(String key, Integer id){
    stringRedisTemplate.opsForGeo().remove(prefix(key),id.toString());
  }



  public <T> void sAdd(String key, T entity){
    stringRedisTemplate.opsForSet().add(prefix(key),JSONUtil.toJSONString(entity));
  }

  public <T> Set<String> sMembers(String key){
    return stringRedisTemplate.opsForSet().members(prefix(key));
  }

  // 更新排行榜
  public void zAdd(String key, Integer userId, Double value) {
    stringRedisTemplate.opsForZSet().add(prefix(key),userId.toString(),value);
  }
  // 增加元素的score值，并返回增加后的值
  public Double zIncrementScore(String key,Integer userId, Double delta){
    return stringRedisTemplate.opsForZSet().incrementScore(key, userId.toString(), delta);
  }
  // 获取排名榜
  public Set<String> range(String key, Integer start, Integer end) {
    return stringRedisTemplate.opsForZSet().range(prefix(key), start, end);
  }
  // 获取排名榜
  public Set<String> reverseRange(String key, Integer start, Integer end){
    return stringRedisTemplate.opsForZSet().reverseRange(prefix(key), start, end);
  }
  // 获取分数
  public Double score(String key, Object value){
    return stringRedisTemplate.opsForZSet().score(prefix(key), value);
  }

  public void delete(String key){
    stringRedisTemplate.delete(prefix(key));
  }

  // 存储在list头部
  public void leftPush(String key, String keyword){
    stringRedisTemplate.opsForList().leftPush(prefix(key),keyword);
  }

  // 获取列表指定范围内的元素
  public List<String> listRange(String key,Long start, Long end){
    return stringRedisTemplate.opsForList().range(prefix(key), start, end);
  }

  // 获取列表长度
  public Long listSize(String key){
    return stringRedisTemplate.opsForList().size(prefix(key));
  }

  // 裁剪list
  public void listTrim(String key){
    stringRedisTemplate.opsForList().trim(prefix(key), 0L, 100L);
  }

  /**
   * 读取后台系统设置信息
   * @param keyName 键名wx-word
   * @param tenantId 租户ID
   * @return
   * key示例 cache10048:setting:wx-work
   */
  public JSONObject getSettingInfo(String keyName,Integer tenantId){
    String key = "cache" + tenantId + ":setting:" + keyName;
    final String cache = stringRedisTemplate.opsForValue().get(key);
    assert cache != null;
    return JSON.parseObject(cache);
  }

  /**
   * KEY前缀
   * cache[tenantId]:[key+id]
   */
  public static String prefix(String key){
    String prefix = "cache";
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      Object object = authentication.getPrincipal();
      if (object instanceof User) {
        final Integer tenantId = ((User) object).getTenantId();
        prefix = prefix.concat(tenantId.toString()).concat(":");
      }
    }
    return prefix.concat(key);
  }

  // 组装key
  public String key(String name,Integer id){
    return name.concat(":").concat(id.toString());
  }
}
