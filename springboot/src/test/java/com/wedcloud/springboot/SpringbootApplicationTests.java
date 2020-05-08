package com.wedcloud.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootApplicationTests {

  @Resource private RedisTemplate redisTemplate;

  @Test
  void contextLoads() {
    redisTemplate.opsForValue().set("user",1);
  }
}
