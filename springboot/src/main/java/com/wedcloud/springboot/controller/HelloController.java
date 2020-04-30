package com.wedcloud.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "hello控制类")
public class HelloController {
  @ApiOperation(value = "hello",httpMethod ="GET")
  @GetMapping("/hello")
  public String getMessage() {
    return "hello";
  }
}
