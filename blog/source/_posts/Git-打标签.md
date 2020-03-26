---
title: Git-打标签
date: 2020-03-26 22:15:42
tags: Git
---
## 列出标签
命令：`git tag` （可带上可选的 -l 选项 --list）

### 轻量标签（lightweight）
轻量标签很像一个不会改变的分支——它只是某个特定提交的引用
<!-- more -->
#### 创建轻量标签
命令：`git tag <tag>`\
例如：`git tag v1.4`

### 附注标签（annotated）
附注标签是存储在 Git 数据库中的一个完整对象， 可以被校验，包含打标签者的名字、电子邮件地址、日期时间， 此外还有一个标签信息，并且可以使用 GNU Privacy Guard （GPG）签名并验证。

#### 创建附注标签
命令：`git tag -a <tag>`
例如：`git tag -a v1.4 -m "my version 1.4"`\
`-m` 选项指定了一条将会存储在标签中的信息。 如果没有为附注标签指定一条信息，Git 会启动编辑器要求你输入信息

#### 查看到标签信息和与之对应的提交信息
命令：`git show <tag>`

### 后期追加标签
对历史提交记录追加标签
1. 执行命令：`git log --pretty=oneline`
2. 找出指定提交记录的 **校验和（SHA-1）**
3. 执行命令：`git tag -a <tag> 校验和`

### 共享标签
默认情况下，git push 命令并不会传送标签到远程仓库服务器上\
所以创建标签后，需要显示将标签推送到远程服务器上,执行命令：`git push <remote> <tag>`\
例如：`git push origin v1.5`

将所有不在远程仓库的标签推送到远程仓库中,使用 `--tags`选项

### 删除标签
删除本地标签，命令：`git tag -d <tagname>`

移除远程仓库的标签
>> 第一种：`git push <remote> :refs/tags/<tagname>`，\
例如：`git push origin :refs/tags/v1.4-lw`

>> 第二种：`git push <remote> --delete <tagname>`

### 检出标签