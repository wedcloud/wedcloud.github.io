package com.wedcloud.service.impl;

import com.wedcloud.service.UserService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author 许海斌
 * @create 2020/5/10 0010 20:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUser() {
        String str="张三";
    System.out.println(str);
        return str;
    }
}
