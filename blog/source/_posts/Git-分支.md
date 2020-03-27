---
title: Git-分支
date: 2020-03-26 22:59:47
tags: Git
---
## 创建分支的意义
可以将各自的工作从开发主线上分离开来，以免影响开发主线

### Git如何保存数据
Git 保存的不是文件的变化或者差异，而是一系列不同时刻的 **快照**

在进行Gi提交时，Git会产生一个提交对象
>> 该提交对象包含一个指向暂存内容快照的指针，还包含了作者的姓名和邮箱、提交时输入的信息以及指向它的父对象的指针

>> *注意*\
首次提交产生的提交对象没有父对象
<!-- more -->

假设现在有一个工作目录，里面包含了三个将要被暂存和提交的文件:
1. 暂存操作会为每一个文件计算校验和（ SHA-1 哈希算法）
2. 然后会把当前版本的文件快照保存到 Git 仓库中 ，最终将校验和加入到暂存区域等待提交
3. 使用 git commit 进行提交操作，此时，Git 会先计算每一个子目录的校验和， 然后在 Git 仓库中这些校验和保存为树对象，随后，Git 便会创建一个提交对象， 它除了包含上面提到的那些信息外，还包含指向这个树对象的指针
4. Git 当前版本快照保存成功

上面的例子在Git 仓库中有五个对象：三个 blob 对象（保存着文件快照）、一个 树 对象 （记录着目录结构和 blob 对象索引）以及一个 提交 对象（包含着指向前述树对象的指针和所有提交信息）
!["首次提交对象及其树结构"](https://git-scm.com/book/en/v2/images/commit-and-tree.png "首次提交对象及其树结构")

做些修改后再次提交(普通提交)，那么这次产生的提交对象会包含一个指向上次提交对象（父对象）的指针
!["提交对象及其父对象"](https://git-scm.com/book/en/v2/images/commits-and-parents.png "提交对象及其父对象")

Git 的分支，其实本质上仅仅是指向提交对象的可变指针
>> 因为每次提交都是一次版本的快照，分支会在每次提交时自动向前移动,那 Git 的分支肯定是指向最新的快照，所以本质上仅仅是指向提交对象的可变指针

!["分支及其提交历史"](https://git-scm.com/book/en/v2/images/branch-and-history.png "分支及其提交历史")

### 分支创建
命令：`git branch <newbranchname>`

会在当前提交对象上创建一个指针：
!["两个指向相同提交历史的分支"](https://git-scm.com/book/en/v2/images/two-branches.png "两个指向相同提交历史的分支")

那么，Git 又是怎么知道当前在哪一个分支上呢？
>> 在 Git 中，它有一个名为 `HEAD` 的特殊指针，指向当前所在的本地分支（译注：将 HEAD 想象为当前分支的别名）
!["HEAD 指向当前所在的分支"](https://git-scm.com/book/en/v2/images/head-to-master.png "HEAD 指向当前所在的分支")
`HEAD` 仍然在 `master` 上，因为 `git branch` 命令仅仅 创建 一个新分支，并不会自动切换到新分支中去

### 查看各个分支当前所指的对象
命令： `git log --oneline --decorate`

### 分支切换
命令： `git checkout <newbranchname>`\
这样 `HEAD` 就指向切换后的分支了

>> 分支切换会改变你工作目录中的文件\
>> 在切换分支时，一定要注意你工作目录里的文件会被改变。 如果是切换到一个较旧的分支，你的工作目录会恢复到该分支最后一次提交时的样子。 如果 Git 不能干净利落地完成这个任务，它将禁止切换分支

### 创建新分支的同时切换过去
命令： `git checkout -b <newbranchname>`

### 删除分支
命令：`git branch -d <newbranchname>`

### 分支的合并
1. 先切换到需要并入的分支
2. 执行命令：`git merge <newbranchname>`

例如：将 `iss53` 合并到 `master`
>> 1. git checkout master
>> 2. git merge iss53

### 遇到冲突时的分支合并
Git 会在有冲突的文件中加入标准的冲突解决标记，这样你可以打开这些包含冲突的文件然后手动解决冲突,出现冲突的文件会包含一些特殊区段，看起来像下面这个样子:
```
<<<<<<< HEAD:index.html
<div id="footer">contact : email.support@github.com</div>
=======
<div id="footer">
 please contact us at support@github.com
</div>
>>>>>>> iss53:index.html
```
`HEAD` 所指示的版本（当前分支）在这个区段的上半部分（======= 的上半部分），而 iss53 分支所指示的版本在 ======= 的下半部分。 为了解决冲突，你必须选择使用由 ======= 分割的两部分中的一个，或者你也可以自行合并这些内容
