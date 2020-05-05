package com.wedcloud.springboot.service.impl;

import com.wedcloud.springboot.service.HelloService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author 许海斌
 * @create 2020/5/2 0002 11:34
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    @Async
    public void getHello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello..."+Thread.currentThread().getName());
    }
}
