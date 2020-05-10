package com.wedcloud.controller;

import com.wedcloud.service.UserService;
import com.wedcloud.service.UserServiceConsumer;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 许海斌
 * @create 2020/5/10 0010 22:15
 */
@RestController
@RequestMapping("/v1")
public class UserController {

    @Resource private UserServiceConsumer consumer;

    @GetMapping("/user")
    public String getUser(){
        return consumer.getUser();
    }

}
