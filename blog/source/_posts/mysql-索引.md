---
title: mysql-索引
tags: [mysql]
categories:
  - mysql
date: 2020-04-09 16:40:06
---

## 索引

查询表的索引信息

```mysql
SHOW INDEX FROM `表名`
```



### 索引分类

* 主键索引（`PRIMARY KEY`）
  - 唯一标识，主键不可重复
* 唯一索引 (`UNIQUE KEY`)
  - 当前字段不可重复
* 常规索引 (`KEY/INDEX`)
  - 默认的
* 全文索引 (`FullText`)
  - 快速定位数据

> 存储过程

### 原则

* 索引不是越多越好
* 不要对经常变动的数据加索引
* 小数据量的表不需要加索引
* 索引一般加在常用来查询的字段