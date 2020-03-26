---
title: Git-别名
date: 2020-03-26 22:41:44
tags: Git
---
## Git 别名

### 创建别名
命令：`git config`
例子：
* $ `git config --global alias.co checkout`
* $ `git config --global alias.br branch`
* $ `git config --global alias.ci commit`
* $ `git config --global alias.st status`

当要输入 git commit 时，只需要输入 git ci
<!-- more -->
为了解决取消暂存文件的易用性问题，可以向 Git 中添加你自己的取消暂存别名
>> `git config --global alias.unstage 'reset HEAD --'`

这会使下面的两个命令等价：
>> git unstage fileA\
>> git reset HEAD -- fileA

再比如，轻松查看最后一次的提交log:`git config --global alias.last 'log -1 HEAD'`

### 执行外部命令
想要执行外部命令，而不是一个 Git 子命令，可以在命令前面加入 `!` 符号

将 git visual 定义为 gitk 的别名：`git config --global alias.visual '!gitk'`

***注意*** \
git 图形化操作工具 -- **gitk** , 是 git 提供的一个gui工具,可以很清晰地查看搜索提交历史及 git 相关操作