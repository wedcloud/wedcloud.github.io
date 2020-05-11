package com.wedcloud.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Author 许海斌
 * @create 2020/5/10 0010 22:29
 */
@Service
public class UserServiceConsumer {
    @Reference
    private UserService service;

    public String getUser(){
        return service.getUser();
    }
}
