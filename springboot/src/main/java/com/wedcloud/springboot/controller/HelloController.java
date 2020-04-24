package com.wedcloud.springboot.controller;

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
public class HelloController {
  @GetMapping("/hello")
  public String getMessage() {
    return "hello";
  }
}
