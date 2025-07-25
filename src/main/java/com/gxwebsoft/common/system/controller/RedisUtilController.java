package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.utils.CacheClient;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis-util")
@Api(tags = "Redis缓存工具接口")
public class RedisUtilController extends BaseController {
  private CacheClient cacheClient;
  private final StringRedisTemplate redisTemplate;
  private static final String SPLIT = ":";
  private static final String PREFIX_ENTITY_LIKE = "focus:user";
  private static final String PREFIX_USER_LIKE = "like:user";
  private static final String PREFIX_FOLLOWEE = "followee";
  private static final String PREFIX_FOLLOWER = "follower";


  public RedisUtilController(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @ApiOperation("添加关注")
  @PostMapping("/addFocus")
  public ApiResult<?> addFocus(@RequestBody User user) {
    final Integer userId = user.getUserId();
    redisTemplate.opsForZSet().incrementScore(getFocusKey(userId), userId.toString(), 1);
    return success("关注成功");
  }

  /**
   * 某个用户的关注数
   * @return like:entity:[entityId] ->set(userId)
   */
  public static String getFocusKey(Integer userId) {
    return PREFIX_ENTITY_LIKE + SPLIT + userId;
  }

  /**
   * 某个用户的赞
   * @return like:entity:[entityId] ->set(userId)
   */
  public static String getEntityLikeKey(int entityType, int entityId) {
    return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
  }

  /**
   * 某个用户的赞
   * @return like:user:[userId] ->int
   */
  public static String getUserLikeKey(int userId) {
    return PREFIX_USER_LIKE + SPLIT + userId;
  }

  /**
   * 某个用户关注的实体（键：用户Id，值：实体Id）
   * @return followee:[userId:entityType] ->zSet(entityId,now)
   */
  public static String getFolloweeKey(int userId, int entityType) {
    return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
  }

  /**
   * 某个实体拥有的粉丝（键：实体Id，值：用户Id)
   * @return follower:[entityType:entityId] ->zSet(entityId,now)
   */
  public static String getFollowerKey(int entityType, int entityId) {
    return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
  }

}
