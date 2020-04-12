---
title: spring-IOC
tags: [spring]
categories:
  - spring
  - ioc
date: 2020-04-12 13:58:54
---

# IOC

### 广义的IOC

* IOC(Inversion of control) 控制反转，即“不用打电话过来，我们会打给你”

  两种实现： 依赖查找（DL）和依赖注入（DI）
  IOC 和 DI 、DL 的关系（这个 DL，Avalon 和 EJB 就是使用的这种方式实现的 IoC）:

  ![](https://upload-images.jianshu.io/upload_images/4236553-c415d1c39ed74772.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)

* DL 已经被抛弃，因为他需要用户自己去使用API 进行查找资源好组装对象，也就是说这个是有入侵性的

* DI 是 Spring 使用的方式，容器负责组件的装配

>注意：Java 使用 DI 方式实现 IoC 的不止 Spring，包括 Google 的 Guice，还有一个冷门的 PicoContainer（极度轻量，但只提供 IoC）



## Spring 的 IOC

Spring 的 IoC 设计支持以下功能:

* 依赖注入
* 依赖检查
* 自动装配
* 支持集合
* 指定初始化方法和销毁方法
* 支持回调某些方法（但是需要实现Spring 接口，略有入侵性）

> 其中，最重要的就是依赖注入，从 XML 的配置上说， 即 ref 标签。对应 Spring RuntimeBeanReference 对象



对于 IoC 来说，最重要的就是容器。容器管理着 Bean 的生命周期，控制着 Bean 的依赖注入。

###  那么， Spring 如何设计容器的呢？

Spring 作者 Rod Johnson 设计了两个接口用以表示容器:

* BeanFactory
*  ApplicationContext



BeanFactory 粗暴简单，可以理解为就是个 HashMap，Key 是 BeanName，Value 是 Bean 实例。通常只提供注册（put），获取（get）这两个功能。我们可以称之为 **`低级容器`**

ApplicationContext 可以称之为 **`高级容器`**。因为他比 BeanFactory 多了更多的功能。他继承了多个接口。因此具备了更多的功能。例如资源的获取，支持多种消息（例如 JSP tag 的支持），对 BeanFactory 多了工具级别的支持等待。所以你看他的名字，已经不是 BeanFactory 之类的工厂了，而是 “应用上下文”， 代表着整个大容器的所有功能。该接口定义了一个 refresh 方法，此方法是所有阅读 Spring 源码的人的最熟悉的方法，用于刷新整个容器，即重新加载/刷新所有的 bean



为了更直观的展示 “低级容器” 和 “高级容器” 的关系，我这里通过常用的 ClassPathXmlApplicationContext 类，来展示整个容器的层级 UML 关系

![](https://upload-images.jianshu.io/upload_images/4236553-1e6ca4c8a58c9e8e.png?imageMogr2/auto-orient/strip|imageView2/2/w/1170/format/webp)

ApplicationContext 粉红色的 “高级容器”，依赖着 “低级容器”，这里说的是依赖，不是继承，他依赖着 “低级容器” 的 getBean 功能。而高级容器有更多的功能：支持不同的信息源头，可以访问文件资源，支持应用事件（Observer 模式）

左边灰色区域的是 “低级容器”， 只负载加载 Bean，获取 Bean。

### IoC 启动过程

![](https://upload-images.jianshu.io/upload_images/4236553-db065eecf16176c3.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)

> 注意，这里为了理解方便，有所简化

#### 用文字来描述这个过程

1. 用户构造 ClassPathXmlApplicationContext（简称 CPAC）
2. CPAC 首先访问了 “抽象高级容器” 的 final 的 refresh 方法，这个方法是模板方法。所以要回调子类（低级容器）的 refreshBeanFactory 方法，这个方法的作用是使用低级容器加载所有 BeanDefinition 和  Properties 到容器中
3. 低级容器加载成功后，高级容器开始处理一些回调，例如 Bean 后置处理器。回调 setBeanFactory 方法。或者注册监听器等，发布事件，实例化单例 Bean 等等功能

简单说就是:

* 低级容器 加载配置文件（从 XML，数据库，Applet），并解析成 BeanDefinition 到低级容器中
* 加载成功后，高级容器启动高级功能，例如接口回调，监听器，自动实例化单例，发布事件等等功能

> 所以，一定要把 “低级容器” 和“高级容器” 的区别弄清楚。不能一叶障目不见泰山

当我们创建好容器，就会使用 getBean 方法，获取 Bean，而 getBean 的流程如下:

![](https://upload-images.jianshu.io/upload_images/4236553-da9a2f92e4dfa9db.png?imageMogr2/auto-orient/strip|imageView2/2/w/530/format/webp)

###### 从图中可以看出，getBean 的操作都是在低级容器里操作的。其中有个递归操作，这个是什么意思呢？

假设 ： 当 Bean_A 依赖着 Bean_B，而这个 Bean_A 在加载的时候，其配置的 ref = “Bean_B” 在解析的时候只是一个占位符，被放入了 Bean_A 的属性集合中，当调用 getBean 时，需要真正 Bean_B 注入到 Bean_A 内部时，就需要从容器中获取这个 Bean_B，因此产生了递归。

为什么不是在加载的时候，就直接注入呢？因为加载的顺序不同，很可能 Bean_A 依赖的 Bean_B 还没有加载好，也就无法从容器中获取，你不能要求用户把 Bean 的加载顺序排列好，这是不人道的。

所以，Spring 将其分为了 2 个步骤:

1. 加载所有的 Bean 配置成 BeanDefinition 到容器中，如果 Bean 有依赖关系，则使用占位符暂时代替

2. 在调用 getBean 的时候，进行真正的依赖注入，即如果碰到了属性是 ref 的（占位符），那么就从容器里获取这个 Bean，然后注入到实例中 —— 称之为依赖注入

> 可以看到，依赖注入实际上，只需要 “低级容器” 就可以实现

所以 ApplicationContext refresh 方法里面的操作不只是 IoC，是高级容器的所有功能（包括 IoC），IoC 的功能在低级容器里就可以实现



**控制反转 是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式，在Spring 中实现控制反转的是IOC容器，其实现方法时依赖注入（Dependency Injection，DI）**

### IOC创建初始化对象

默认使用对象的无参构造器进行初始化对象，也可以手动调用有参或无参构造函数初始化对象；

###### 初始化时期

在xml文件加载的时候，容器中管理的对象就已经初始化了，而且是单例的；