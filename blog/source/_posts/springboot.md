---
title: springboot
tags: [springboot]
categories:
  - springboot
date: 2020-04-23 14:35:56
---

# springboot

## 自动配置

@SpringBootApplication

* @SpringBootConfiguration ：表示该类是spring组件
* @EnableAutoConfiguration ：开启自动装配
  * @AutoConfigurationPackage ：添加该注解的类所在的packag 作为自动配置package到spring容器中
    * @Import(AutoConfigurationPackages.Registrar.class)：将组件注册到spring中
  * @Import(AutoConfigurationImportSelector.class) ：导入组件的选择器

> 怎么过滤：
>
> @ConditionalOnClass ： classpath中存在该类时起效
> @ConditionalOnMissingClass ： classpath中不存在该类时起效
> @ConditionalOnBean ： DI容器中存在该类型Bean时起效
> @ConditionalOnMissingBean ： DI容器中不存在该类型Bean时起效
> @ConditionalOnSingleCandidate ： DI容器中该类型Bean只有一个或@Primary的只有一个时起效
> @ConditionalOnExpression ： SpEL表达式结果为true时
> @ConditionalOnProperty ： 参数设置或者值一致时起效
> @ConditionalOnResource ： 指定的文件存在时起效
> @ConditionalOnJndi ： 指定的JNDI存在时起效
> @ConditionalOnJava ： 指定的Java版本存在时起效
> @ConditionalOnWebApplication ： Web应用环境下起效
> @ConditionalOnNotWebApplication ： 非Web应用环境下起效

### SpringFactoriesLoader

- loadFactories：加载指定的factoryClass并进行实例化。
- loadFactoryNames：加载指定的factoryClass的名称集合。
- instantiateFactory：对指定的factoryClass进行实例化。

