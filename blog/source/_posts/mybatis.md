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

##### SQL（比较重要，组件化）

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

> 1、最好基于单表来定义SQL判断
>
> 2、不要存在where标签

##### 参数

Map传递参数时，直接在sql中取出key即可【parameterType="map"】

对象传递参数时，直接在sqk中取出对象的属性即可【parameterType="pojo"】

只有一个基本参数的情况下，可以直接在sql中取到【parameterType 可以不写】



### 结果映射

- `constructor` - 用于在实例化类时，注入结果到构造方法中
  - `idArg` - ID 参数；标记出作为 ID 的结果可以帮助提高整体性能
  - `arg` - 将被注入到构造方法的一个普通结果
- `id` – 一个 ID 结果；标记出作为 ID 的结果可以帮助提高整体性能
- `result` – 注入到字段或 JavaBean 属性的普通结果
- `association` – 一个复杂类型的关联；许多结果将包装成这种类型
  - 嵌套结果映射 – 关联可以是 `resultMap` 元素，或是对其它结果映射的引用
- `collection` – 一个复杂类型的集合
  - 嵌套结果映射 – 集合可以是 `resultMap` 元素，或是对其它结果映射的引用
- `discriminator` – 使用结果值来决定使用哪个`resultMap`
  - `case` – 基于某些值的结果映射
    - 嵌套结果映射 – `case` 也是一个结果映射，因此具有相同的结构和元素；或者引用其它的结果映射

#### id & result

| 属性        | 描述                                                         |
| ----------- | ------------------------------------------------------------ |
| property    | 映射到列结果的字段或属性                                     |
| column      | 数据库中的列名，或者是列的别名                               |
| javaType    | 一个 Java 类的全限定名，或一个类型别名                       |
| jdbcType    | JDBC 类型，所支持的 JDBC 类型参见这个表格之后的“支持的 JDBC 类型” |
| typeHandler | 属性值是一个类型处理器实现类的全限定名，或者是类型别名       |

id 和 result 元素都将一个列的值映射到一个属性字段，id 元素对应的属性会被标记为对象的标识符，在比较对象实例时使用。 这样可以提高整体的性能，尤其是进行缓存和嵌套结果映射（也就是连接映射）的时候。

#### 关联（association）

```xml
<association property="author" column="blog_author_id" javaType="Author">
  <id property="id" column="author_id"/>
  <result property="username" column="author_username"/>
</association>
```

| 属性        | 描述                                                   |
| ----------- | ------------------------------------------------------ |
| property    | 映射到列结果的字段或属性                               |
| javaType    | 一个 Java 类的全限定名，或一个类型别名                 |
| jdbcType    | JDBC 类型                                              |
| typeHandler | 属性值是一个类型处理器实现类的全限定名，或者是类型别名 |

MyBatis 有两种不同的方式加载关联：

* 嵌套 Select 查询：通过执行另外一个 SQL 映射语句来加载期望的复杂类型
* 嵌套结果映射：使用嵌套的结果映射来处理连接结果的重复子集

##### 嵌套 Select 查询

| 属性      | 描述                                                         |
| --------- | ------------------------------------------------------------ |
| column    | 数据库中的列名，或者是列的别名。<br />一般情况下，这和传递给 `resultSet.getString(columnName)` 方法的参数一样 |
| select    | 用于加载复杂类型属性的映射语句的 ID，它会从 column 属性指定的列中检索数据，作为参数传递给目标 select 语句 |
| fetchType | 可选的。有效值为 `lazy` 和 `eager`。 指定属性后，将在映射中忽略全局配置参数 `lazyLoadingEnabled`，使用属性的值 |

>注意：在使用复合主键的时候，你可以使用 `column="{prop1=col1,prop2=col2}"` 这样的语法来指定多个传递给嵌套 Select 查询语句的列名。这会使得 `prop1` 和 `prop2` 作为参数对象，被设置为对应嵌套 Select 语句的参数

```xml
<resultMap id="blogResult" type="Blog">
  <association property="author" column="author_id" javaType="Author" select="selectAuthor"/>
</resultMap>

<select id="selectBlog" resultMap="blogResult">
  SELECT * FROM BLOG WHERE ID = #{id}
</select>

<select id="selectAuthor" resultType="Author">
  SELECT * FROM AUTHOR WHERE ID = #{id}
</select>
```

这种方式虽然很简单，但在大型数据集或大型数据表上表现不佳。这个问题被称为`“N+1 查询问题”`

> `N+1`问题:
>
> 1.你执行了一个单独的 SQL 语句来获取结果的一个列表（就是“+1”）
>
> 2.对列表返回的每条记录，你执行一个 select 查询语句来为每条记录加载详细信息（就是“N”）
>
> 这个问题会导致成百上千的 SQL 语句被执行

MyBatis 能够对这样的查询进行延迟加载，因此可以将大量语句同时运行的开销分散开来。 然而，如果你加载记录列表之后立刻就遍历列表以获取嵌套的数据，就会触发所有的延迟加载查询，性能可能会变得很糟糕



##### 嵌套结果映射（推荐）

| 属性          | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| resultMap     | 1.结果映射的 ID，可以将此关联的嵌套结果集映射到一个合适的对象树中。<br /> 2.它可以作为使用额外 select 语句的替代方案。它可以将多表连接操作的结果映射成一个单一的 `ResultSet`。这样的 `ResultSet` 有部分数据是重复的。<br /> 3.为了将结果集正确地映射到嵌套的对象树中, MyBatis 允许你“串联”结果映射，以便解决嵌套结果集的问题 |
| columnPrefix  | 1.当连接多个表时，你可能会不得不使用列别名来避免在 `ResultSet` 中产生重复的列名<br />2.指定 columnPrefix 列名前缀允许你将带有这些前缀的列映射到一个外部的结果映射中 |
| notNullColumn | 1.默认情况下，在至少一个被映射到属性的列不为空时，子对象才会被创建<br />2.你可以在这个属性上指定非空的列来改变默认行为，指定后，Mybatis 将只在这些列非空时才创建一个子对象。可以使用逗号分隔来指定多个列。默认值：未设置（unset）。 |
| autoMapping   | 1.如果设置这个属性，MyBatis 将会为本结果映射开启或者关闭自动映射。 这个属性会覆盖全局的属性 autoMappingBehavior。<br />2.注意，本属性对外部的结果映射无效，所以不能搭配 `select` 或 `resultMap` 元素使用。<br />3.默认值：未设置（unset）。 |

```sql
<select id="selectBlog" resultMap="blogResult">
  select
    B.id            as blog_id,
    B.title         as blog_title,
    B.author_id     as blog_author_id,
    A.id            as author_id,
    A.username      as author_username,
    A.password      as author_password,
    A.email         as author_email,
    A.bio           as author_bio
  from Blog B left outer join Author A on B.author_id = A.id
  where B.id = #{id}
</select>
```

```xml
<resultMap id="blogResult" type="Blog">
  <id property="id" column="blog_id" />
  <result property="title" column="blog_title"/>
  <association property="author" column="blog_author_id" javaType="Author" resultMap="authorResult"/>
</resultMap>

<resultMap id="authorResult" type="Author">
  <id property="id" column="author_id"/>
  <result property="username" column="author_username"/>
  <result property="password" column="author_password"/>
  <result property="email" column="author_email"/>
  <result property="bio" column="author_bio"/>
</resultMap>
```

##### 关联的多结果集（ResultSet）

| 属性          | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| column        | 当使用多个结果集时，该属性指定结果集中用于与 `foreignColumn` 匹配的列（多个列名以逗号隔开），以识别关系中的父类型与子类型 |
| foreignColumn | 指定外键对应的列名，指定的列将与父类型中 `column` 的给出的列进行匹配 |
| resultSet     | 指定用于加载复杂类型的结果集名字                             |

调用存储过程（某些数据库允许存储过程返回多个结果集，或一次性执行多个语句，每个语句返回一个结果集）

```xml
<select id="selectBlog" resultSets="blogs,authors" resultMap="blogResult" statementType="CALLABLE">
  {call getBlogsAndAuthors(#{id,jdbcType=INTEGER,mode=IN})}
</select>
```

```xml
<resultMap id="blogResult" type="Blog">
  <id property="id" column="id" />
  <result property="title" column="title"/>
  <association property="author" javaType="Author" resultSet="authors" column="author_id" foreignColumn="id">
    <id property="id" column="id"/>
    <result property="username" column="username"/>
    <result property="password" column="password"/>
    <result property="email" column="email"/>
    <result property="bio" column="bio"/>
  </association>
</resultMap>
```

#### 集合

java：

```java
private List<Post> posts;
```

xml：

```xml
<collection property="posts" ofType="domain.blog.Post">
  <id property="id" column="post_id"/>
  <result property="subject" column="post_subject"/>
  <result property="body" column="post_body"/>
</collection>
```



##### 集合的嵌套 Select 查询

```xml
<resultMap id="blogResult" type="Blog">
  <collection property="posts" javaType="ArrayList" column="id" ofType="Post" select="selectPostsForBlog"/>
</resultMap>

<select id="selectBlog" resultMap="blogResult">
  SELECT * FROM BLOG WHERE ID = #{id}
</select>

<select id="selectPostsForBlog" resultType="Post">
  SELECT * FROM POST WHERE BLOG_ID = #{id}
</select>
```

> `ofType="Post"` 读作 “posts 是一个存储 Post 的 ArrayList 集合”

在一般情况下，MyBatis 可以推断 javaType 属性，因此并不需要填写。所以很多时候你可以简略成：

```xml
<collection property="posts" column="id" ofType="Post" select="selectPostsForBlog"/>
```

##### 集合的嵌套结果映射（推荐）

集合的嵌套结果映射是怎样工作的——除了新增的 “ofType” 属性，它和关联的完全相同

##### 集合的多结果集（ResultSet）

像关联元素那样，可以通过执行存储过程实现，它会执行两个查询并返回两个结果集