---
title: mybatis
tags: [mybatis]
categories:
  - mybatis
date: 2020-04-19 22:22:17
---

## mybatis

* MyBatis 是一款优秀的`持久层框架`
* 它支持自定义 SQL、存储过程以及高级映射
* MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作
* MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录



### 优点

* 简单易学
* 灵活
* sql与代码分离，提高可维护性
* 提供映射标签，支持对象与数据库的orm关系映射
* 提供对象关系映射标签，支持对象关系组维护
* 提供xml标签，支持编写动态sql

### mybatis XML

* namespace 与 Mapper接口全限定名一直（`com.mypackage.UserMapper`）

#### XML标签

* cache - 该命名空间的缓存配置
* cache-ref  - 引用其他命名空间的缓存配置
* resultMap - 描述如何从数据库结果集中加载对象，是最复杂也是最强大的元素
*  sql - 可被其它语句引用的可重用语句块
* insert - 映射插入语句
* update - 映射更新语句
* delete - 映射删除语句
* select - 映射查询语句

##### Select

```xml
<select
  id="selectPerson"
  parameterType="int"
  parameterMap="deprecated"
  resultType="hashmap"
  resultMap="personResultMap"
  flushCache="false"
  useCache="true"
  timeout="10"
  fetchSize="256"
  statementType="PREPARED"
  resultSetType="FORWARD_ONLY">
```

| 属性          | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| id            | 在命名空间中唯一的标识符，可以被用来引用这条语句             |
| parameterType | 将会传入这条语句的参数的类全限定名或别名（可选）             |
| resultType    | 返回结果的类全限定名或别名。 <br/>注意，如果返回的是集合，那应该设置为集合包含的类型，而不是集合本身的类型。<br/> resultType 和 resultMap 之间只能同时使用一个 |
| resultMap     | 对外部 resultMap 的命名引用。结果映射是 MyBatis 最强大的特性， resultType 和 resultMap 之间只能同时使用一个 |
| flushCache    | 将其设置为 true 后，只要语句被调用，都会导致本地缓存和二级缓存被`清空`，默认值：false |
| useCache      | 将其设置为 true 后，将会导致本条语句的结果被二级缓存`缓存`起来，默认值：对 select 元素为 true |
| timeout       | 这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为未设置（unset）（依赖数据库驱动） |
| statementType | 可选 <br />STATEMENT（直接操作sql，不进行预编译，获取数据：$），<br />PREPARED（预处理，参数，进行预编译，获取数据：# ），<br /> CALLABLE（执行存储过程）<br />默认值：PREPARED |

##### insert , update 和 delete

```xml
<insert
  id="insertAuthor"
  parameterType="domain.blog.Author"
  flushCache="true"
  statementType="PREPARED"
  keyProperty=""
  keyColumn=""
  useGeneratedKeys=""
  timeout="20">

<update
  id="updateAuthor"
  parameterType="domain.blog.Author"
  flushCache="true"
  statementType="PREPARED"
  timeout="20">

<delete
  id="deleteAuthor"
  parameterType="domain.blog.Author"
  flushCache="true"
  statementType="PREPARED"
  timeout="20">
```

| 属性             | 描述                                                         |
| ---------------- | ------------------------------------------------------------ |
| id               | 在命名空间中唯一的标识符，可以被用来引用这条语句             |
| parameterType    | 将会传入这条语句的参数的类全限定名或别名（可选）             |
| flushCache       | 将其设置为 true 后，只要语句被调用，都会导致本地缓存和二级缓存被清空，默认值：（对 insert、update 和 delete 语句）true |
| timeout          | 在抛出异常前，等待超时设置                                   |
| statementType    | 可选 STATEMENT，PREPARED 或 CALLABLE，默认：PREPARED         |
| useGeneratedKeys | （仅适用于 insert 和 update）这会令 MyBatis 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键，默认：false |
| keyProperty      | （仅适用于 insert 和 update）指定能够唯一识别对象的属性，MyBatis 会使用 getGeneratedKeys 的返回值或 insert 语句的 selectKey 子元素设置它的值，如果生成列不止一个，可以用逗号分隔多个属性名称 |
| keyColumn        | （仅适用于 insert 和 update）设置生成键值在表中的列名，在某些数据库（像 PostgreSQL）中，当主键列不是表中的第一列的时候，是必须设置的。如果生成列不止一个，可以用逗号分隔多个属性名称 |
| databaseId       | 如果配置了数据库厂商标识（databaseIdProvider），MyBatis 会加载所有不带 databaseId 或匹配当前 databaseId 的语句；如果带和不带的语句都有，则不带的会被忽略 |

##### selectKey

```xml
<selectKey
  keyProperty="id"
  resultType="int"
  order="BEFORE"
  statementType="PREPARED">
```

| 属性          | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| keyProperty   | `selectKey` 语句结果应该被设置到的目标属性。如果生成列不止一个，可以用逗号分隔多个属性名称 |
| keyColumn     | 返回结果集中生成列属性的列名。如果生成列不止一个，可以用逗号分隔多个属性名称 |
| resultType    | 结果的类型                                                   |
| order         | 可以设置为 `BEFORE` 或 `AFTER`。如果设置为 `BEFORE`，那么它首先会生成主键，设置 `keyProperty` 再执行插入语句。如果设置为 `AFTER`，那么先执行插入语句，然后是 `selectKey` 中的语句 |
| statementType | 可选 STATEMENT，PREPARED 或 CALLABLE，默认：PREPARED         |

##### SQL

这个元素可以用来定义可重用的 SQL 代码片段，以便在其它语句中使用。 参数可以静态地（在加载的时候）确定下来，并且可以在不同的 include 元素中定义不同的参数值

```xml
<sql id="userColumns"> ${alias}.id,${alias}.username,${alias}.password </sql>
```

```xml
<select id="selectUsers" resultType="map">
  select
    <include refid="userColumns"><property name="alias" value="t1"/></include>,
    <include refid="userColumns"><property name="alias" value="t2"/></include>
  from some_table t1
    cross join some_table t2
</select>
```



##### 参数

Map传递参数时，直接在sql中取出key即可【parameterType="map"】

对象传递参数时，直接在sqk中取出对象的属性即可【parameterType="pojo"】

只有一个基本参数的情况下，可以直接在sql中取到【parameterType 可以不写】