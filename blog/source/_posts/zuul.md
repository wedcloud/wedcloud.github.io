---
title: zuul
tags: [zuul]
categories:
  - zuul
date: 2020-05-19 14:32:43
---

# ZUUL

## 什么是zuul

Zuul是Spring Cloud全家桶中的微服务API网关。

所有从设备或网站来的请求都会经过Zuul到达后端的Netflix应用程序。作为一个边界性质的应用程序，Zuul提供了动态路由、监控、弹性负载和安全功能。Zuul底层利用各种filter实现如下功能：

- 认证和安全 识别每个需要认证的资源，拒绝不符合要求的请求。
- 性能监测 在服务边界追踪并统计数据，提供精确的生产视图。
- 动态路由 根据需要将请求动态路由到后端集群。
- 压力测试 逐渐增加对集群的流量以了解其性能。
- 负载卸载 预先为每种类型的请求分配容量，当请求超过容量时自动丢弃。
- 静态资源处理 直接在边界返回某些响应

## 路由配置

Zuul提供了一套简单且强大路由配置策略，利用路由配置我们可以完成对微服务和URL更精确的控制。

1、重写指定微服务的访问路径：

```yml
zuul:
  routes:
	rest-demo: /rest/**
```

这表示将rest-demo微服务的地址映射到/rest/**路径。

2、忽略指定微服务：

```yml
zuul:
  ignored-services: rest-demo,xxx-service
```

使用“*”可忽略所有微服务，多个指定微服务以半角逗号分隔。

3、忽略所有微服务，只路由指定微服务：

```yml
zuul:
  ignored-services: *
  routes:
    rest-demo: /rest/**
```

4、路由别名：

```yml
zuul:
  routes:
    route-name: #路由别名，无其他意义，与例1效果一致
      service-id: rest-demo
      path: /rest/**
```

5、指定path和URL

```yml
zuul:
  routes:
    route-name:
      url: http://localhost:8000/
      path: /rest/**
```

此例将`http://ZUULHOST:ZUULPORT/rest`映射到`http://localhost:8000/`。同时由于并非用service-id定位服务，所以也无法使用负载均衡功能。

6、即指定path和URL，又保留Zuul的Hystrix、Ribbon特性

```yml
zuul:
  routes:
    route-name: #路由别名，无其他意义，与例1效果一致
      service-id: rest-demo
      path: /rest/**
ribbon:
  eureka:
    enable: false #为Ribbon禁用Eureka
rest-demo:
  ribbon:
    listOfServers: localhost:9000,localhost:9001
```

 

7、借助`PatternServiceRouteMapper`实现路由的正则匹配

```java
@Bean
public PatternServiceRouteMapper serviceRouteMapper(){
    /**
     * A RegExp Pattern that extract needed information from a service ID. Ex :
     * "(?<name>.*)-(?<version>v.*$)"
     */
    //private Pattern servicePattern;
    /**
     * A RegExp that refer to named groups define in servicePattern. Ex :
     * "${version}/${name}"
     */
    //private String routePattern;
    return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
}
```

此例可以将rest-demo-v1映射为/v1/rest-demo/**。

8、路由前缀

```ynl
zuul:
  prefix: /api
  strip-prefix: true
  routes:
    rest-demo: /rest/**
```

此时访问Zuul的/api/rest/user/xdlysk会被转发到/rest-demo/user/xdlysk。

9、忽略某些微服务中的某些路径

```yml
zuul:
  ignoredPatterns: /**/user/* #忽略所有包含/user/的地址请求
  routes:
    route-demo:
      service-Id: rest-demo
      path: /rest/**
```

> 忽略：指的是访问中会屏蔽指定的微服务