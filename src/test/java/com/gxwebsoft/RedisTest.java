package com.gxwebsoft;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {
  @Resource
  private StringRedisTemplate stringRedisTemplate;
//
//  @Test
//  public void test(){
////      stringRedisTemplate.opsForValue().set("test:add:2",Long.toString(1L));
////      stringRedisTemplate.opsForValue().increment("test:add:2",10L);
////      stringRedisTemplate.opsForValue().decrement("test:add:2",2L);
////    stringRedisTemplate.opsForValue().append("test:add:2","ssss");
//    HashMap<String, String> map = new HashMap<>();
//    map.put("name","李四");
//    map.put("phone","13800138001");
//    HashMap<String, String> map2 = new HashMap<>();
//    map2.put("name","赵六");
//    map2.put("phone","13800138001");
//    HashMap<String, String> map3 = new HashMap<>();
//    map3.put("name","张三");
//    map3.put("phone","13800138001");
//    stringRedisTemplate.opsForSet().add("test:set:2", JSONUtil.toJSONString(map),JSONUtil.toJSONString(map2),JSONUtil.toJSONString(map3));
//  }
}
