---
title: Maven-生命周期
tags: [Maven]
categories:
  - Maven
date: 2020-05-12 14:44:49
---

# Maven 生命周期

## 三套生命周期

Maven拥有三套相互独立的生命周期，分别为clean、default、site。

- 每个生命周期都包含一些有序的阶段， 并且后面的阶段依赖前面的阶段，用户和Maven最直接的交互方式就是调用这些生命周期阶段。
- 三个生命周期是相互独立的，例如当用户调用clean生命周期的某个阶段，不会触发default生命周期的任何阶段，反之亦然，当用户调用default生命周期的时候，不会出发clean生命周期的任何阶段

### clean生命周期（在进行真正的构建工作之前做一些清理的工作）

* pre-clean：执行一些清理前需要完成的工作
* clean：清理上一次构建的文件
* post-clean：执行一些清理后需要完成的工作

### default生命周期（构建的核心部分，编译、测试、打包、部署、发布等）

* validate：验证该项目是正确的，并且确定所有必须的信息都是可用的
* initialize：初始化构建状态，例如创建目标目
* generate-sources：生成要编译的任何源代码
* process-source：处理源代码
* generate-resources：生成要打包在包里的资源文件
* process-resources：将资源文件处理并复制到目标文件夹
* compile：编译项目主代码
* process-classes：处理编译后的.class文件
* generate-test-sources：如上
* process-test-sources：如上
* generate-test-resources：如上
* process-test-resources：如上
* test-compile：编译项目的测试代码
* process-test-classes：如上
* test：使用单元测试框架运行，测试代码不会被打包或部署
* prepare-package：打包预处理
* package：打包
* pre-integration-test：继承测试之前准备
* integration-test：集成测试
* post-integration-test：集成测试后扫尾操作，例如清除运行时用到的环境变量
* verify：检查是否打包标准且成功
* install：将包安装在Maven本地仓库
* deploy：将最终的包复制到远程仓库

### site生命周期（生成项目报告、站点、发布站点）

* pre-site：执行一些在生成项目站点之前的操作
* site：生成项目站点文档
* post-site：执行一些在生成项目站点之后需要完成的操作
* site-deploy：将生成的项目站点发布到服务器上

## 命令行和生命周期

* $mvn clean：执行clean生命周期的clean阶段
* $ mvn test：执行default生命周期的test阶段，会default生命周期的第一个阶段执行到test阶段
* $mvn clean install：执行clean生命周期的clean阶段和default生命周期的install阶段
* $mvn clean deploy site-deploy：执行clean生命周期的clean阶段，执行default生命周期的deploy阶段，执行site生命周期的site-deploy阶段

>Maven命令的话基本就是mvn加生命周期，所以掌握Maven的生命周期，就会对Maven有一定的了解

## 插件配置

插件的配置主要有两种方式：

- 命令行插件配置：
  - Maven命令中使用-D参数，并伴随一个参数键=参数值的形式
  - 例如常用的mvn clean package -Dmaven.test.skip=true
- POM文件配置
  - 配置插件的全局配置，如配置compile插件的编译版本
  - 为某个插件任务配置特定的参数



## Maven聚合与继承

### 聚合

>产生背景：有时候我们的一个项目包含很多个模块，如淘宝系统，包含订单模块、用户模块等。如果我们要启动淘宝这个大项目，那就需要将很多个模块一个一个启动，费时费力。聚合主要的作用就是将所有的小模块聚合起来，只需要启动一次即可启动整个项目。
>
>简单来说，聚合就是为了一次构建多个项目这种需求而服务的。

* 聚合Maven项目的打包方式为Maven
* 使用<modules><module>标签引入子模块；module的值为子模块的相对路径
* 一般聚合项目只包含POM文件，不会在聚合项目中编写项目代码

### 集成

>产生背景：有时候我们很多的项目都用了同样的依赖，如很多项目都使用了spring，所以基于编程继承的思想，Maven也有了继承的概念

一般我们想的时将公共的依赖部分写进父项目中，然后继承它，但是这里有个问题，因为不确定之后的子模块是否需要该依赖。

- 这种情况的话可使用<dependencyManagement>标签来解决该问题
  - dependency Manager元素下依赖声明不会引入实际的依赖，不过如果在子类继承了父类后，虽然不会实际的引入依赖，但是会继承<dependencyManager>声明的配置，从而简化配置。
- 同样还提供<pluginManagement>实现以上功能