---
title: Git-DIFF
date: 2020-03-26 16:09:24
tags: Git
---

## Git diff 详细使用

### git diff
    当工作区有改动，临时区为空，diff的对比是“工作区与最后一次commit提交的仓库的共同文件”；当工作区有改动，临时区不为空，diff对比的是“工作区与暂存区的共同文件

### git diff --cached 或 git diff --staged(两者作用相同)
    显示暂存区(已add但未commit文件)和最后一次commit(HEAD)之间的所有不相同文件的增删改

### git diff HEAD
    显示工作目录(已track但未add文件)和暂存区(已add但未commit文件)与最后一次commit之间的的所有不相同文件的增删改

### git diff <分支名1> <分支名2> 
    比较两个分支上最后 commit 的内容的差别

### git diff branch1 branch2
    显示出所有有差异的文件的详细差异(更详细)

### git diff branch1 branch2 具体文件路径
    显示指定文件的详细差异(对比内容)