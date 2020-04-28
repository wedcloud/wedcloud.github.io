package com.wedcloud.springboot.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * <h3>blog</h3>
 *
 * <p>控制层切面
 *
 * <p>通知执行顺序 around > before > around > after > afterReturning
 *
 * @author : 许先生
 * @date : 2020-04-27 09:04
 */
@Aspect
@Component
public class ControllerAspect {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Pointcut("execution(* com.wedcloud.springboot.controller.*.*(..))")
  public void pointCut() {}

  @Before("pointCut()")
  public void before(JoinPoint joinPoint) {
    // 这个RequestContextHolder是Springmvc提供来获得请求的东西
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

    StringBuilder requestLog = new StringBuilder();
    Signature signature = joinPoint.getSignature();
    requestLog
        .append("请求信息：")
        .append("URL = {")
        .append(request.getRequestURI())
        .append("},\t")
        .append("请求方式 = {")
        .append(request.getMethod())
        .append("},\t")
        .append("请求IP = {")
        .append(request.getRemoteAddr())
        .append("},\t")
        .append("类方法 = {")
        .append(signature.getDeclaringTypeName())
        .append(".")
        .append(signature.getName())
        .append("},\t");

    // 处理请求参数
    String[] paramNames = ((MethodSignature) signature).getParameterNames();
    Object[] paramValues = joinPoint.getArgs();
    int paramLength = null == paramNames ? 0 : paramNames.length;
    if (paramLength == 0) {
      requestLog.append("请求参数 = {} ");
    } else {
      requestLog.append("请求参数 = [");
      for (int i = 0; i < paramLength - 1; i++) {
        requestLog
            .append(paramNames[i])
            .append("=")
            .append(JSONObject.toJSONString(paramValues[i]))
            .append(",");
      }
      requestLog
          .append(paramNames[paramLength - 1])
          .append("=")
          .append(JSONObject.toJSONString(paramValues[paramLength - 1]))
          .append("]");
    }
    logger.info(requestLog.toString());
  }

  @After("pointCut()")
  public void after() {
    System.out.println("after");
  }

  @Around("pointCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("around");
    return joinPoint.proceed();
  }

  @AfterReturning(pointcut = "pointCut()", returning = "response")
  public void afterReturning(ResponseEntity response) {
    logger.info("请求结果：" + response.getBody());
  }

  @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
  public void afterThrowing(JoinPoint joinPoint, Exception ex) {
    String methodName = joinPoint.getSignature().getName();
    List<Object> args = Arrays.asList(joinPoint.getArgs());
    logger.error("连接点方法为：{},参数为：{},异常为：{}", methodName, args, ex);
  }
}
