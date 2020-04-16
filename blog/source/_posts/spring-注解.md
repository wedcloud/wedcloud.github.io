---
title: spring-注解
tags: [spring]
categories:
  - spring
  - 注解
date: 2020-04-16 14:28:28
---

## 注解

使用注解开发，必须导入AOP的包；

@component （把普通pojo实例化到spring容器中，相当于配置文件中的`<bean id="" class=""/>`）

>@component衍生注解：
>
>* dao层 【@Repository】
>* service层 【@Service】
>* controller层 【@Controller】
>
>这四个注解都是一样的，都代表将某个类注册到spring容器中，装配bean

@Value("value") 将value注入到属性中 

@Scope 配置作用域 （`singleton`,`prototype`等）

### xml 与 注解

xml使用面广，适用于任何场合，维护简单方便

注解只能使用自己的类，维护相对复杂

XML与注解最佳实践：

- xml管理bean
- 注解只负责完成属性注入