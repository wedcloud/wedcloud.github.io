package com.wedcloud.springboot.aspect;

import com.wedcloud.springboot.annotation.Token;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h3>blog</h3>
 * <p>Token面向切面注解</p>
 *
 * @author : 许先生
 * @date : 2020-05-06 10:48
 **/
@Aspect
@Component
public class TokenAspect {

    private static final Logger LOG= LoggerFactory.getLogger(TokenAspect.class);

    @Pointcut("@annotation(com.wedcloud.springboot.annotation.Token)")
    public void tokenCut(){}

    @Before("tokenCut() && @annotation(token)")
    public void before(JoinPoint joinPoint, Token token){
        LOG.debug(token.value());
    }
}
