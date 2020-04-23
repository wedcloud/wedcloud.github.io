package com.wedcloud.springboot;

import com.wedcloud.springboot.pojo.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootApplicationTests {

  @Resource Animal animal;

  @Test
  void contextLoads() {
    System.out.println(animal);
  }
}
