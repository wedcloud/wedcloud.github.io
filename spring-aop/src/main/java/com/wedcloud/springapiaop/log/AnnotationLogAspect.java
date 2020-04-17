package com.wedcloud.springapiaop.log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * <h3>blog</h3>
 * <p>注解切面</p>
 *
 * @author : 许先生
 * @date : 2020-04-17 14:08
 **/
@Aspect
public class AnnotationLogAspect {
    @After("execution(* com.wedcloud.springapiaop.service.impl.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("after");
    }

    @Before("execution(* com.wedcloud.springapiaop.service.impl.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("before");
    }
}
