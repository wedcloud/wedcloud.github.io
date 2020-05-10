package com.wedcloud;

import com.wedcloud.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumerApplicationTests {

  @Reference private UserService service;

  @Test
  void contextLoads() {
    System.out.println(service.getUser());
  }
}
