---
title: Git-记录每次更新到仓库
date: 2020-03-25 23:02:50
tags:
---

## 状态
Git 仓库中文件的状态 `已跟踪` 和 `未跟踪`;
>> 已跟踪的文件是指那些被纳入了版本控制的文件

>> 未跟踪文件就是在工作目录中除已跟踪文件外的其它所有文件
<!-- more -->
!["文件变化周期"](https://git-scm.com/book/en/v2/images/lifecycle.png "文件变化周期")


## 查看当前文件状态

命令 >> `git status`

输出：
```
On branch master
Your branch is up-to-date with 'origin/master'.
nothing to commit, working directory clean
```
表示所有文件都在已跟踪状态,并且指明了分支（master）

输出：
```
On branch master
Your branch is up-to-date with 'origin/master'.
Untracked files:
  (use "git add <file>..." to include in what will be committed)

    README

nothing added to commit but untracked files present (use "git add" to track)
```
`Untracked files` 下面列出未跟踪的文件，并提示你如果需要跟踪，则需要提交

## 跟踪新文件(将文件提交到暂存区)
命令 >> `git add <files>` 

>> `files`表示文件或目录的路径；如果参数是目录的路径，该命令将递归地跟踪该目录下的所有文件

此命令将文件添加到`暂存区`

使用`git status` 查看状态，`Changes to be committed` 下面列表，表示这些均是已暂存状态

## 暂存已修改的文件
将文件放入暂存区后，又修改了这个文件，这是使用 `git status` 会发现这个文件同时出现在 `暂存区` 和 `非暂存区`。

如果现在提交，那这个文件版本是你最后一次运行 git add 命令时的那个版本，而不是你运行 git commit 时，在工作目录中的当前版本;

若不想出现这种情况，则需要再次执行 `git add <files>` ，将文件最新版本放入暂存区。

实际上 Git 只是暂存了你运行 git add 命令时的版本

## 状态简览
命令 >> `git status -s ` 或 `git status --short`
输出：
```
 M README
MM Rakefile
A  lib/git.rb
M  lib/simplegit.rb
?? LICENSE.txt
```
输出中有两栏，左栏指明了暂存区的状态，右栏指明了工作区的状态

* `??` 新添加的未跟踪文件 
* `A` 新添加到暂存区中的文件
* `M` 修改过的文件

## 忽略文件
不需要被Git管理的文件，可以将其记录在 `.gitignore` 的文件中

文件 .gitignore 的格式规范如下：

* 所有空行或者以 # 开头的行都会被 Git 忽略。
* 可以使用标准的 glob 模式匹配，它会递归地应用在整个工作区中。
* 匹配模式可以以（/）开头防止递归。
* 匹配模式可以以（/）结尾指定目录。
* 要忽略指定模式以外的文件或目录，可以在模式前加上叹号（!）取反

`glob` 模式 ： 指 shell 所使用的简化了的正则表达式;
* 星号（*）匹配零个或多个任意字符
* [abc] 匹配任何一个列在方括号中的字符 （这个例子要么匹配一个 a，要么匹配一个 b，要么匹配一个 c）
* 问号（?）只匹配一个任意字符
* 如果在方括号中使用短划线分隔两个字符， 表示所有在这两个字符范围内的都可以匹配（比如 [0-9] 表示匹配所有 0 到 9 的数字）
*  使用两个星号（`**`）表示匹配任意中间目录，比如 a/**/z 可以匹配 a/z 、 a/b/z 或 a/b/c/z 等

## 查看已暂存和未暂存的修改
命令 >> `git diff` 
>> 此命令比较的是工作目录中当前文件和暂存区域快照之间的差异,也就是只显示尚未暂存的改的

## 提交更新
命令 >> `git commit`
>> 将暂存区的文件提交到Git仓库中

>> -m 表示提交信息，将提交信息与命令放在同一行

提交前可以使用 `git status` 查看是否还有遗漏的文件未被 `add` 到暂存区

## 跳过使用暂存区域

提交的时候，给 git commit 加上 -a 选项，Git 就会自动把所有已经跟踪过的文件暂存起来一并提交

## 移除文件
命令 >> `git rm <文件或者目录>` ,也可以使用 glob 模式
>> 从工作目录中删除指定的文件

>> -f 删除之前修改过或已经放到暂存区的文件

只将文件从缓存区移除，依然保留在工作目录中：`--cached`

## 文件重命名
命令 >> `git mv oldfile newfile`