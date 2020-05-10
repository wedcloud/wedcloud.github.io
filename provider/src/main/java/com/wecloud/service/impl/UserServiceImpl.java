package com.wecloud.service.impl;

import com.wecloud.service.UserService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author 许海斌
 * @create 2020/5/10 0010 20:00
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public String getUser() {
        String str="张三";
    System.out.println(str);
        return str;
    }
}
