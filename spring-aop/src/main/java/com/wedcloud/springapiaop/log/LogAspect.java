package com.wedcloud.springapiaop.log;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.BeforeAdvice;

/**
 * <h3>blog</h3>
 * <p>日志切面</p>
 *
 * @author : 许先生
 * @date : 2020-04-17 13:51
 **/
public class LogAspect implements AfterAdvice, BeforeAdvice {
    public void after(){
        System.out.println("after");
    }

    public void before(){
        System.out.println("before");
    }
}
