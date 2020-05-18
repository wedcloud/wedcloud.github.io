---
title: Ribbon
tags: [Ribbon]
categories:
  - Ribbon
date: 2020-05-18 13:31:51
---

# Ribbon

## 默认配置

```
IClientConfig：Ribbon 的客户端配置，默认采用 com.netflix.client.config.DefaultClientConfigImpl 实现。
IRule：Ribbon 的负载均衡策略，默认采用 com.netflix.loadbalancer.ZoneAvoidanceRule 实现，该策略能够在多区域环境下选择最佳区域的实例进行访问
IPing：Ribbon 的实例检查策略，默认采用 com.netflix.loadbalancer.NoOpPing 实现，该检查策略是一个特殊的实现，实际上他并不会检查实例是否可用，而是始终返回 true ，默认认为所有服务实例都是可以使用
ServerList<Server>：服务实例清单的维护机制，默认采用 com.netflix.loadbalancer.ConfigurationBasedServerList 实现。
ServerListFilter<Server>：服务实例清单过滤机制，默认采用 org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter 实现，该策略能够优先过滤出与请求调用方处理同区域的服务实现
ILoadBalancer：负载均衡器，默认采用 com.netflix.loadbalancer.ZoneAwareLoadBalancer 实现，他具备了区域感知的能力
```



## @RibbonClient

name 指定需要参与负载均衡的微服务名称

**`服务名称的字母需要小写`**

