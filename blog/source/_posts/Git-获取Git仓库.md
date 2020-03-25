---
title: Git-获取Git仓库
date: 2020-03-25 22:34:11
tags: Git
---

# 获取Git仓库的两种方式

1. 将尚未进行版本控制的本地目录转换为 Git 仓库
2. 从服务器 **克隆** 一个已经存在的 Git 仓库
<!-- more -->
## 本地转为 Git 仓库

1. 进入到项目根目录下,如：`cd /home/user/my_project`
2. 执行初始化，命令：`git init`
>> 产生 **.git** 的隐藏文件，含有你初始化的 Git 仓库中所有的必须文件，是核心
3. 将项目添加到暂存区，命令：`git add *`
4. 提交更新，命令：`git commit -m "<描述信息>"`

##  克隆 Git 仓库

1. 克隆仓库命令 `git clone <url> [自定义本地仓库名字]`

Git 支持多种协议，https、http、ssh等，各有利弊