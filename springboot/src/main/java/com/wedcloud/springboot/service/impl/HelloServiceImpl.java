package com.wedcloud.springboot.service.impl;

import com.wedcloud.springboot.service.HelloService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author 许海斌
 * @create 2020/5/2 0002 11:34
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    @Async
    @CachePut(value = "hello",key = "#id")
    public String getHello(String id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello..."+Thread.currentThread().getName();
    }
}
