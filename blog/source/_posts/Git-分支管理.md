---
title: Git-分支管理
date: 2020-03-27 13:50:51
tags: Git
categories: 
- Git
---
## 分支管理

### 查看本地分支
命令：`git branch` 
>> `--merged` （已合并） 与 `--no-merged` （未合并） 这两个有用的选项可以过滤这个列表中已经合并或尚未合并到当前分支的分支

注意 
>> `master` 分支前的 `*` 字符：它代表现在检出的那一个分支（也就是说，当前 HEAD 指针所指向的分支）
<!-- more -->
### 查看每一个分支的最后一次提交
命令：`git branch -v`

### NOTE
上面描述的选项 --merged 和 --no-merged 会在没有给定提交或分支名作为参数时， 分别列出已合并或未合并到 当前 分支的分支。

你总是可以提供一个附加的参数来查看其它分支的合并状态而不必检出它们。 例如，尚未合并到 master 分支的有哪些？
```
$ git checkout testing
$ git branch --no-merged master
  topicA
  featureB
```