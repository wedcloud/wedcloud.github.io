---
title: spring
tags: [spring]
categories:
  - spring
date: 2020-04-10 08:52:54
---

## Spring

> 解决企业级开发的复杂性，使现有的技术更加容易使用



### 优点

* Spring 开源免费的框架（容器）
* 轻量级、非入侵式的框架
* 控制反转（`IOC`），面向切面编程（`AOP`）
* 支持事务
* 框架整合的支持



### 7大模块

![image-20200410090322458](spring/image-20200410090322458.png)

1. 核心容器：核心容器提供 Spring 框架的基本功能。核心容器的主要组件是 `BeanFactory`，它是工厂模式的实现。`BeanFactory` 使用控制反转 （`IOC`） 模式将应用程序的配置和依赖性规范与实际的应用程序代码分开
2. `Spring` 上下文：Spring 上下文是一个配置文件，向 Spring 框架提供上下文信息。Spring 上下文包括企业服务，例如 `JNDI`、`EJB`、电子邮件、国际化、校验和调度功能
3. `Spring AOP`：通过配置管理特性，`Spring AOP` 模块直接将面向方面的编程功能集成到了 Spring 框架中。所以，可以很容易地使 Spring 框架管理的任何对象支持 `AOP`。`Spring AOP` 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 `Spring AOP`，不用依赖` EJB` 组件，就可以将声明性事务管理集成到应用程序中
4. `Spring DAO`：`JDBC DAO` 抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量（例如打开和关闭连接）。`Spring DAO` 的面向 `JDBC `的异常遵从通用的 `DAO` 异常层次结构
5. `Spring ORM`：Spring 框架插入了若干个`ORM` 框架，从而提供了 `ORM` 的对象关系工具，其中包括 `JDO`、`Hibernate` 和 `iBatis SQL Map`。所有这些都遵从 Spring 的通用事务和 `DAO` 异常层次结构
6. `Spring Web` 模块：Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文。所以，Spring 框架支持与 Jakarta Struts 的集成。Web 模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作
7. `Spring MVC` 框架：`MVC` 框架是一个全功能的构建 Web 应用程序的 `MVC` 实现。通过策略接口，`MVC` 框架变成为高度可配置的，`MVC` 容纳了大量视图技术，其中包括 `JSP`、`Velocity`、`Tiles`、`iText` 和 `POI`
