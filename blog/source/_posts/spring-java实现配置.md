---
title: spring-java实现配置
tags: [spring]
categories:
  - spring
  - javaConfig
date: 2020-04-16 14:57:43
---

## 使用Java的方式配置Spring

POJO

```java
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("123")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```



CONFIG

```java
@Configuration
public class JavaConfig {

    @Bean
    public User getUser(){
        return new User();
    }
}
```



Test

```java
public class JavaConfigTest {
  public static void main(String[] args) {
      ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
      User user = context.getBean("getUser",User.class);
      System.out.println(user);
  }
}
```

