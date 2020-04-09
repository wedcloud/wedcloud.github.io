---
title: mysql-基础知识
date: 2020-04-08 09:30:42
tags: [mysql]
categories: 
- mysql
---

# mysql

## 基础命令

1. 连接数据库

   ```mysql
   mysql -uroot -p<密码>;
   ```

2. 修改密码

   ```mysql 
   ALTER USER 'root'@'localhost' IDENTIFIED WITH MYSQL_NATIVE_PASSWORD BY '新密码';
   ```

   > 注意：数据库存储密码时，需要加密

3. 刷新权限

   ```mysql
   flush privileges;
   ```

4. 显示数据库中所有的表的信息

    ```mysql
        describe `表名`;
    ```

5. 退出连接

    ```mysql
    exit;
    ```

6. 注释

    ```mysql
    单行注释 -- 注释内容
    多行注释 /* 注释内容 */
    ```
    
7. `EXPLAIN` 分析SQL

> 所有的语句都需要使用 ； 分好结尾

## 操作数据库

### 操作数据库

1、创建数据库

```mysql
create database [if not exists] `数据库名`；
```

2、删除数据库

```mysql
drop database [if exists] `数据库名`
```
3、查看所有的数据库

   ```mysql
show databases;
   ```

4、使用数据库

```mysql
use `数据库名`;
```

5、数据库排序规则

* 特征
  - 两个不同的字符集不能有相同的排序规则
  - 两个字符集有一个默认的排序规则
  - 有一些常用的命名规则。如_ci结尾表示大小写不敏感（caseinsensitive）,_cs表示大小写敏感（case sensitive）,_bin表示二进制的比较（binary）
* 区别
  - utf8_general_ci 不区分大小写，这个你在注册用户名和邮箱的时候就要使用
  - utf8_general_cs 区分大小写，如果用户名和邮箱用这个 就会照成不良后果
  - utf8_bin:字符串每个字符串用二进制数据编译存储。 区分大小写，而且可以存二进制的内容
  - utf8_general_ci校对速度快，但准确度稍差
  - utf8_unicode_ci准确度高，但校对速度稍慢
* 使用情况
  utf8_unicode_ci比较准确，utf8_general_ci速度比较快。通常情况下，新建数据库时一般选用utf8_general_ci就可以了

### 用户

1. 用户权限赋值

    ```mysql
    grant all privileges on '表名'.* to '用户名'@'%';
    ```
### 所用

2. 普通索引

    ```mysql
    ALTER TABLE '表名' ADD INDEX 索引名(`字段名`);
    ```



### 数据类型

> 数值

| 数据类型  | 数据大小                 | 字节  |
| --------- | ------------------------ | ----- |
| tinyint   | 十分小的数据库           | 1     |
| smallint  | 较小数据                 | 2     |
| mediumint | 中等                     | 3     |
| **int**   | **标准**                 | **4** |
| bigint    | 较大                     | 8     |
| float     | 浮点                     | 4     |
| double    | 浮点                     | 8     |
| decimal   | 字符串形式浮点数（精度） |       |

**注意**

​	int(a)，a只与`Zerofill  `(0填充) 有关

> 字符串

| 数据类型    | 数据解释       | 字节        |
| ----------- | -------------- | ----------- |
| char        | 定长字符串     | 0~255       |
| **varchar** | **可变字符串** | **0~65535** |
| tinytext    | 微型文本       | 2^8-1       |
| **text**    | **文本串**     | **2^16-1**  |



> 时间日期

| 数据类型  | 解释      | 格式                   |
| --------- | --------- | ---------------------- |
| date      | 日期      | YYYY-MM-DD             |
| time      | 时间      | HH:mm:ss               |
| datetime  | 日期+时间 | YYYY-MM-DD HH:mm:ss    |
| timestamp | 时间戳    | 1970.1.1到现在的毫秒数 |



> null

* 没有值
* ==不要使用NULL进行运算，NULL参与运算结果始终为NULL==

## 数据字段属性

* 默认 ，不设置值时，数据库会自动在当前字段添加上默认值
* 主键
* 非空
* Unsigned 无符号，字段要求是整形
* 自增
* Zerofill  不足位数使用0填充，字段要求是整形





## 数据库表

### 创建表

```mysql
CREATE TABLE [IF NOT EXISTS] `表名` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT '匿名' COMMENT '名字',
  `cancel` int(11) DEFAULT '0' COMMENT '0位删除 1已删除',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `modified_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT COMMENT='中文表名'

```



* `IF NOT EXISTS`  如果不存在
* `IF EXISTS` 如果存在
* `AUTO_INCREMENT` 自增关键字
* `DEFAULT` 默认值设置
* `COMMENT` 备注
* `PRIMARY KEY (字段名)`设置主键
* `ENGINE`设置表引擎
* `AUTO_INCREMENT` 自增主键初始值
* `CHARSET`字符集



### InnoDB和MyISAM

| 功能       | MyISAM | InnoDB              |
| ---------- | ------ | ------------------- |
| 事务支持   | 不支持 | 支持                |
| 数据行锁定 | 不支持 | 支持                |
| 外键约束   | 不支持 | 支持                |
| 全文索引   | 支持   | 不支持              |
| 表空间大小 | 较小   | 较大，约MyISAM的2倍 |

常规操作：

 * MyISAM 节约空间，速度快
 * INNODB 安全性高，事务的处理，多表多用户操作

### 表的修改与删除

1. 修改表名

   ```mysql
   alter table `旧表名` rename as `新表名`
   ```

   

2. 增加表字段

   ```mysql
   alter table `表名` add `字段名` `列属性（int（11））`
   ```

   

3. 修改表字段

   * 修改约束

     ```mysql
     alter table `表名` modify `字段名` `新的列属性`
     ```

   * 字段重命名

     ```mysql
     alter table `表名` change `旧字段名` `新字段名` [`新的列属性`]
     ```

     

4. 删除表字段

   ```mysql
   alter table `表名` drop `字段名`
   ```

5. 删除表

   ```mysql
   drop table `表名`
   ```

   

6. 清空表

   ```mysql
   truncate `表名`
   ```

   >delete和truncate区别
   >
   >相同点：都不会删除表结构，只会删除数据
   >
   >不同： 
   >
   >1. truncate 会将自增列计数器归零
   >
   >     		 2. truncate 不会影响事务，也就是删除了数据无法回滚
   >          		 3. 性能：truncate > delete

### 查询

1. 去重：`distinct`
2. 查询版本 : `select version()`
3. 计算：`select 100*3-1