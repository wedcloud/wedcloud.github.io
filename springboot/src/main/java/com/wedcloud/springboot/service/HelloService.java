package com.wedcloud.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author 许海斌
 * @create 2020/5/2 0002 11:32
 */
public interface HelloService {
    /**
     * 输出Hello
     * @return void
     */
    void getHello();
}
