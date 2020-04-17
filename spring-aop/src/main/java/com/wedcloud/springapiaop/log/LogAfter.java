package com.wedcloud.springapiaop.log;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * <h3>blog</h3>
 * <p>日志 方法执行后</p>
 *
 * @author : 许先生
 * @date : 2020-04-17 13:28
 **/
public class LogAfter implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getClass().getName()+" -->LogAfter --> return "+returnValue);
    }
}
