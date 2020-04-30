package com.wedcloud.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * springboot 启动主类
 *
 * @author spring
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.wedcloud.springboot.mapper")
public class SpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }
}
