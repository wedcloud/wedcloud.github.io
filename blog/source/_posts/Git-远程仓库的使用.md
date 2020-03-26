---
title: Git-远程仓库的使用
date: 2020-03-26 21:32:48
tags: Git
---
 ## 查看远程仓库
 查看已经配置好了的远程仓库,命令：`git remote`。

 如果你已经克隆了自己的仓库，那么至少应该能看到 `origin` ——这是 Git 给你克隆的仓库服务器的默认名字。

 指定选项 -v，会显示需要读写远程仓库使用的 Git 保存的简写与其对应的 URL。

 ## 添加远程仓库
 1. 自行添加远程仓库，`git clone`
 2. 手动添加仓库：`git remote add <shortname> <url>` ,`shortname` 远程仓库别名（默认 origin）
 
 ## 从远程仓库中抓取与拉取
 命令：`git fetch <remote>`
 只会拉取远程仓库中的数据，不会自动进行合并，若需要合并，则需要手动执行。

 ## 推送到远程仓库
 命令：`git push <remote> <branch>` \
 例如：`git push origin master` \
 有冲突需要先解决冲突才能提交

 ## 查看某个远程仓库
 命令：`git remote show <remote>`