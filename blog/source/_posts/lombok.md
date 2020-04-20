---
title: lombok
tags: [lombok]
categories:
  - lombok
date: 2020-04-20 09:32:58
---

## lombok

### 常规注解

* @Data

  > 注解在类上，会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法

* @Getter/@Setter

  > 注解在属性上，可以为相应的属性自动生成Getter/Setter方法

* @NonNull

  >注解用在属性或构造器上，Lombok会生成一个非空的声明，可用于校验参数，能帮助避免空指针

* @Cleanup

  >注解能帮助我们自动调用close()方法，很大的简化了代码
  >
  >例：
  >
  >```java
  >@Cleanup InputStream in = new FileInputStream(args[0]);
  >    @Cleanup OutputStream out = new FileOutputStream(args[1]);
  >```

* @EqualsAndHashCode

  > 注解在类上，默认情况下，会使用所有非静态（non-static）和非瞬态（non-transient）属性来生成equals和hasCode，也能通过exclude注解来排除一些属性
  >
  > 例：@EqualsAndHashCode(exclude={"id", "shape"})

* @ToString

  > 类使用@ToString注解，Lombok会生成一个toString()方法，默认情况下，会输出类名、所有属性（会按照属性定义顺序），用逗号来分割
  >
  > 通过将`includeFieldNames`参数设为true，就能明确的输出toString()属性

* @NoArgsConstructor，@RequiredArgsConstructor ，@AllArgsConstructor

  > 无参构造器、部分参数构造器、全参构造器
  >
  > 注意：Lombok没法实现多种参数构造器的重载