package com.wedcloud.springboot.controller;

import com.wedcloud.springboot.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>hellow world
 *
 * @author : 许先生
 * @date : 2020-04-24 11:11
 */
@RestController
public class HelloController {

  @Resource
  private HelloService service;

  @ApiOperation(value = "hello",httpMethod ="GET")
  @GetMapping("/hello")
  public String getMessage() {
    service.getHello();
    return "hello";
  }

  public HelloService getService() {
    return service;
  }

  public void setService(HelloService service) {
    this.service = service;
  }
}
