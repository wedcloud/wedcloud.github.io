---
title: mysql-聚合函数
tags: [mysql]
categories:
  - mysql
date: 2020-04-09 11:01:39
---



## 聚合函数

| 名字     | 描述   |
| -------- | ------ |
| count(*) | 统计   |
| SUM()    | 求和   |
| AVG()    | 平均值 |
| MAX()    | 最大值 |
| MIN()    | 最小值 |
|          |        |

> count(字段名)，会忽略null值
>
> count(1/\*) 不会忽略null，性能上count(1)和count(*)基本没有差别



### 分组

1. group by ... having ...



## MYSQL MD5

主要是增强算法复杂度和不可逆性

