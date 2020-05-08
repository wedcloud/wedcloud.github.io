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

* @ComponentScan：扫描范围->同级包以及所有的子包
* @SpringBootConfiguration ：表示该类是spring组件
* @EnableAutoConfiguration ：开启自动配置（扫包范围默认当前类）
  * @AutoConfigurationPackage ：添加该注解的类所在的packag 作为自动配置package到spring容器中
    * @Import(AutoConfigurationPackages.Registrar.class)：自动搜索当前类所在包下的注解实体
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



## redis

springDataRedis

1.X版本使用jedis

> jedis:采用直连，多个线程操作的话，是不安全的，避免多线程不安全需要使用jedis pool连接池

2.x版本使用lettuce

> lettuce：使用netty，示例可以在多个线程中进行共享，不存在线程不安全的情况，可以减少线程数据

### redisConfig

```java
@Configuration // redis配置类
@EnableCaching // 开启缓存
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        // StringRedisSerializer 简单的字符串序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // FastJsonRedisSerializer 阿里巴巴 fastjosn
        FastJsonRedisSerializer jsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);

        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
```

### 配置文件

```yaml
spring:
  redis:
    host: 127.0.0.1
    password: 123456
    port: 6379
    database: 0
    timeout: 1000
```

