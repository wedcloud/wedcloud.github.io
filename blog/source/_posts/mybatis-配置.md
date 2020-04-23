---
title: mybatis-配置
tags: [mybatis]
categories:
  - mybatis
date: 2020-04-20 22:36:09
---

## mybatis 配置

### 属性

![image-20200420231109395](mybatis-配置/image-20200420231109395.png)

引用

![image-20200420231217964](mybatis-配置/image-20200420231217964.png)

MyBatis 将按照下面的顺序来加载：

- 首先读取在 properties 元素体内指定的属性。
- 然后根据 properties 元素中的 resource 属性读取类路径下属性文件，或根据 url 属性指定的路径读取属性文件，并覆盖之前读取过的同名属性。
- 最后读取作为方法参数传递的属性，并覆盖之前读取过的同名属性。

因此，通过方法参数传递的属性具有最高优先级，resource/url 属性中指定的配置文件次之，最低优先级的则是 properties 元素中指定的属性。



### 别名

类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写

```xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
  <typeAlias alias="Blog" type="domain.blog.Blog"/>
  <typeAlias alias="Comment" type="domain.blog.Comment"/>
  <typeAlias alias="Post" type="domain.blog.Post"/>
  <typeAlias alias="Section" type="domain.blog.Section"/>
  <typeAlias alias="Tag" type="domain.blog.Tag"/>
</typeAliases>
```

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean

```xml
<typeAliases>
  <package name="domain.blog"/>
</typeAliases>
```

每一个在包 `domain.blog` 中的 Java Bean，在没有注解的情况下，会使用 Bean 的首字母小写的非限定类名来作为它的别名。 比如 `domain.blog.Author` 的别名为 `author`；若有注解，则别名为其注解值

```java
@Alias("author")
public class Author {
    ...
}
```

### 设置

| 设置名                   | 描述                                                         | 有效值     | 默认值 |
| ------------------------ | ------------------------------------------------------------ | ---------- | ------ |
| cacheEnabled             | 全局性地开启或关闭所有映射器配置文件中已配置的任何缓存       | true/false | true   |
| lazyLoadingEnabled       | 延迟加载的全局开关。<br />当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 `fetchType` 属性来覆盖该项的开关状态 | true/false | false  |
| useGeneratedKeys         | 允许 JDBC 支持自动生成主键                                   | true/false | false  |
| mapUnderscoreToCamelCase | 是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn | true/false | false  |

### 映射器

```xml
<!-- 使用相对于类路径的资源引用（推荐使用） -->
<mappers>
  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
  <mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
```

```xml
<!-- 不要使用 --->
<!-- 使用完全限定资源定位符（URL） -->
<mappers>
  <mapper url="file:///var/mappers/AuthorMapper.xml"/>
  <mapper url="file:///var/mappers/BlogMapper.xml"/>
  <mapper url="file:///var/mappers/PostMapper.xml"/>
</mappers>
```

```xml
<!-- 使用映射器接口实现类的完全限定类名 -->
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
<!-- 
注意：
1.接口和对应的Mapper.xml文件必须同名
2.接口和Mapper.xml必须在同一个包下
-->
```

```xml
<!-- 将包内的映射器接口实现全部注册为映射器 -->
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
<!-- 
注意：
1.接口和对应的Mapper.xml文件必须同名
2.接口和Mapper.xml必须在同一个包下
-->
```



### 作用域

作用域和生命周期类别是至关重要的，因为错误的使用会导致非常严重的`并发问题`

**SqlSessionFactoryBuilder**

> 1.只需要创建一次
>
> 2.最佳作用域是方法作用域（也就是局部方法变量）

**SqlSessionFactory**

> 1.创建后就一直存在
>
> 2.最佳作用域是应用作用域（application 全局）
>
> 3.最简单的就是使用单例模式或者静态单例模式

**SqlSession**

> 1.SqlSession 的实例不是线程安全的，所以每个线程都应该有自己的SqlSession 
>
> 2.最佳的作用域是请求或方法作用域
>
> 3.使用后需要及时关闭，避免资源长期占有