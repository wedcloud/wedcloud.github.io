---
title: springcloud
tags: [springcloud]
categories:
  - springcloud
date: 2020-05-12 08:59:49
---

# springcloud

微服务，是一种架构思想，将应用模块化

产生原因：网络不可靠

## 微服务架构4个核心问题？

* 服务很多，客户端怎么访问？--> API Getway 网关
* 多服务之间怎么通讯？ --> RPC 或 Http
* 如何治理？ --> 注册中心、监控中心
* 服务挂了怎么办？ --> 熔灾机制

## 微服务方案

### spring Cloud Netflix

> 一站式解决方案
>
> 1. api网关，zuul组件
> 2. Feign --> HttpClient --> http通讯方式
> 3. 服务注册发现： Eureka
> 4. 熔断机制：Hystrix

### Apache Dubbo Zookeeper

> 半自动，需要整合第三方
>
> 1. api网关，需要使用第三方或手动实现
> 2. 通讯：dubbo
> 3. 服务注册、发现：Zookeeper
> 4. 熔断机制：可以借助Hystrix

### Spring Cloud Alibaba

> 新的一站式解决方案，更简单
>
> 1. 



`新概念：服务网格（Server Mesh） --> istio`

### 微服务优缺点

#### 优点

* 每个服务足够内聚、足够小，代码容易理解，能聚焦一个指定的业务功能和业务需求
* 开发简单，开发效率高，单一职责原则，每个单一服务可以就只干一件事情
* 微服务是松耦合，可以独立部署
* 可以使用不同的语言开发
* 易于进而第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，jenkins,Hudson,bamboo
* 易于修改和维护
* 每个微服务只是业务逻辑，不涉及页面相关
* 每个微服务都有自己的存储能级，可以有自己的数据库，也可以有统一的数据库

#### 缺点

* 开发人员要处理分布式系统的复杂性
* 多服务运维难度，随着服务的增加，运维的压力也增大
* 系统部署依赖
* 服务器之间的通讯成本
* 数据一致性
* 系统集成测试
* 性能监控

## 微服务技术栈

| 名称                                     | 技术                                                         |
| ---------------------------------------- | ------------------------------------------------------------ |
| 服务开发                                 | springboot、spring、springMVC                                |
| 服务配置与管理                           | Netflix公司的Archaius，阿里的Diamond等                       |
| 服务注册与发现                           | Eureka、Consul、Zookeeper、Nacos                             |
| 服务调用                                 | Rest、RPC、gRPC                                              |
| 服务熔断                                 | Hystrix、Envoy等                                             |
| 负载均衡                                 | Nginx、Ribbon等                                              |
| 服务接口调用（客户端调用服务的简化工具） | Feign等                                                      |
| 消息队列                                 | Kafka、RabbitMQ、ActiveMQ等                                  |
| 服务配置中心管理                         | springCloudConfig、Chef等                                    |
| 服务路由（API网关）                      | Zuul等                                                       |
| 服务监控                                 | Zabbix、Nagios、Metrics、Specatator等                        |
| 全链路追踪                               | Zipkin、Brave、Dapper等                                      |
| 服务部署                                 | Docker、OpenStack、Kubernetes等                              |
| 数据流操作开发包                         | SpringCloud Stream（封装与Redis、Rabbit、Kafka等发送接收消息） |
| 事件消息总线                             | SpringCloud Bus                                              |

### 为何选择SpringCloud 作为微服务架构

1. 选型依据

   * 整体解决方案和框架成熟度
   * 社区热度、社区活跃度
   * 可维护性
   * 学习曲线

2. 各大公司微服务架构

   * 阿里： dubbo+HFS
   * 京东：JSF
   * 新浪：Motan
   * 当当网：DubboX

3. 微服务框架对比

   | 功能\框架      | Netflix/SpringCloud                                          | Motan                                                       | gRpc                      | Thrift   | Dubbo/DubboX                      |
   | -------------- | ------------------------------------------------------------ | ----------------------------------------------------------- | ------------------------- | -------- | --------------------------------- |
   | 功能定位       | 完整的微服务框架                                             | RPC框架（可以整合ZK或Consul，实现集群环境的服务注册与发现） | RPC框架                   | RPC框架  | 服务框架                          |
   | Rest支持       | 是，Ribbon支持多种可插拔的序列化选择                         | 否                                                          | 否                        | 否       | 否                                |
   | RPC支持        | 否（可以兼容dubbo,支持RPC）                                  | 是（Hession2）                                              | 是                        | 是       | 是                                |
   | 多语言支持     | 是                                                           | 否                                                          | 是                        | 是       | 否                                |
   | 负载均衡       | 是（服务端zuul+客户端Ribbon）,zuul服务，动态路由，云端负载均衡Eureka（针对中间层服务器） | 是（客户端）                                                | 否                        | 否       | 是（客户端）                      |
   | 配置服务       | Netflix Archaius，Spring Cloud Config Server 集中配置        | 是（Zookeeper提供）                                         | 否                        | 否       | 否                                |
   | 服务调用链监控 | 是，zuul 提供边缘服务，API网关                               | 否                                                          | 否                        | 否       | 否                                |
   | 高可用/容错    | 服务端Hystrix+客户端Ribbon                                   | 是（客户端）                                                | 否                        | 否       | 是（客户端）                      |
   | 典型应用案例   | Netflix                                                      | Sina                                                        | Google                    | Facebook |                                   |
   | 社区活跃度     | 高                                                           | 一般                                                        | 高                        | 一般     | 2017年重新开始维护，之前中断了5年 |
   | 学习难道       | 中                                                           | 低                                                          | 高                        | 高       | 低                                |
   | 文档丰富程度   | 高                                                           | 一般                                                        | 一般                      | 一般     | 高                                |
   | 其他           | SpringCLoud Bus为引用程序带来更多管理端点                    | 支持降级                                                    | Netflix内部在开发集成gRPC | IDL定义  | 实践公司比较多                    |

   

### SpringBoot 与 SpringCloud版本对应关系

https://start.spring.io/actuator/info