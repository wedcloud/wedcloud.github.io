package com.wedcloud.springapiaop.log;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * <h3>blog</h3>
 * <p>日志 方法执行前</p>
 *
 * @author : 许先生
 * @date : 2020-04-17 11:29
 **/
public class LogBefore implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println(method.getClass().getName()+" -->LogBefore -->");
    }
}
