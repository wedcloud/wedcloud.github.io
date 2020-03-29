---
title: Git-起步
date: 2020-03-24 22:50:44
tags: Git
categories: 
- Git
---

## 安装Git

`安装最新版Git` --> [点击跳转](https://git-scm.com "点击跳转")
或自行[百度](https://www.baidu.com)
<!-- more -->
## 初次运行Git前的配置
Git 自带一个 git config 的工具来帮助设置控制 Git 外观和行为的配置变量。 

这些变量存储在三个不同的位置：
>> * /etc/gitconfig 文件: 包含系统上每一个用户及他们仓库的通用配置。 如果在执行 git config 时带上 --system 选项，那么它就会读写该文件中的配置变量。 （由于它是系统配置文件，因此你需要管理员或超级用户权限来修改它。）

>> * ~/.gitconfig 或 ~/.config/git/config 文件：只针对当前用户。 你可以传递 --global 选项让 Git 读写此文件，这会对你系统上 所有 的仓库生效。

>> * 当前使用仓库的 Git 目录中的 config 文件（即 .git/config）：针对该仓库。 你可以传递 --local 选项让 Git 强制读写此文件，虽然默认情况下用的就是它。。 （当然，你需要进入某个 Git 仓库中才能让该选项生效。）

每一个级别会覆盖上一级别的配置，所以 .git/config 的配置变量会覆盖 /etc/gitconfig 中的配置变量。

查看所有的配置以及它们所在的文件：
>> $ git config --list --show-origin

## 用户信息
安装完 Git 之后，要做的第一件事就是设置你的用户名和邮件地址:
>> git config --global user.name "your name"
>> git config --global user.email your email

再次强调，如果使用了 --global 选项，那么该命令只需要运行一次，就会对整个系统生效，之后无论你在该系统上做任何事情， Git 都会使用那些信息。 

当你想针对特定项目使用不同的用户名称与邮件地址时，可以在那个项目目录下运行没有 --global 选项的命令来配置。

## 文本编辑器
使用Emacs：
>>  git config --global core.editor emacs

在 Windows 系统上，如果你想要使用别的文本编辑器，那么必须指定可执行文件的完整路径
>> git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"

## 检查配置信息
如果想要检查你的配置，可以使用 git config --list 命令来列出所有 Git 当时能找到的配置,
你可能会看到重复的变量名，因为 Git 会从不同的文件中读取同一个配置（例如：/etc/gitconfig 与 ~/.gitconfig）。 这种情况下，Git 会使用它找到的每一个变量的最后一个配置

你可以通过输入 git config <key>： 来检查 Git 的某一项配置
>> git config user.name

## 获取帮助
>> * $ git help `<verb>`
>> * $ git `<verb>` --help
>> * $ man git-`<verb>`

要想获得 git config 命令的手册，执行
>> $ git help config