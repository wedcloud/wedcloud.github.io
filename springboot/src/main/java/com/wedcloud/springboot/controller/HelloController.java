package com.wedcloud.springboot.controller;

import com.wedcloud.springboot.annotation.Token;
import com.wedcloud.springboot.service.HelloService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  private static final Logger LOG= LoggerFactory.getLogger(HelloController.class);

  @Resource
  private HelloService service;

  @ApiOperation(value = "hello",httpMethod ="GET")
  @GetMapping("/hello/{id}")
  @Token("测试")
  public String getMessage(@PathVariable("id") String id) {
    service.getHello(id);
    LOG.info("测试->{}",this.getClass().getPackage());
    return "hello";
  }

  public HelloService getService() {
    return service;
  }

  public void setService(HelloService service) {
    this.service = service;
  }
}
