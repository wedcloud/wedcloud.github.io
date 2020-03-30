---
title: Java并发编程实战-02
date: 2020-03-30 22:49:32
tags: [Java,并发]
categories: 
- Java
- 并发
- 多线程
---
## 线程的安装型

要编写线程安全的代码，其核心在于对状态访问操作进行管理，特别是对共享（Shared）和可变的（Mutable）状态的访问

“共享”意味着变量可以由多个线程同时访问\
“可变”意味着变量的值在其生命周期内可以发生变化
<!-- more -->

### 处理不可控的状态变量
1. 不在线程之间共享该状态变量
2. 状态变量修改为不可变的变量
3. 访问变量事使用同步机制

**当设计线程安全的类时，良好的面向对象技术、不可修改性、以及明确的 `不变性规范` 都能起到一定的帮助作用**

>> 当调代码中不需要额外的同步或协同，这个类都能表现出正确的行为，那么就称这个类是线程安全的

最常见的竞态（race condition）条件类型就是：`先检查后执行操作`，即通过一个可能失效的观测结果来决定下一步的动作

### 内置锁重入
例子：子类重写父类的`synchronized`方法，父类方法锁住的对象是谁？
```
public class SynchronizedTest {
  public static void main(String[] args) {
    Cat c = new Cat();
    c.fun();
  }
}

class Animal {
  public synchronized void fun() {
    System.out.println("Animal -->" + this);
  }
}

class Cat extends Animal {
  @Override
  public synchronized void fun() {
    super.fun();
    System.out.println("Cat -->" + this);
  }
}
```
结果：
```
Animal -->com.wedcloud.mylearn.syn.Cat@5b2133b1
Cat -->com.wedcloud.mylearn.syn.Cat@5b2133b1
```
`super` 和 `this` 以及父类的 `this` 居然是同一个引用,所以才会说锁住的是同一个对象。也就是super本身仍然是子类的引用，只不过它可以调用到父类的方法或变量。

**`this` 指的是实例对象，多态原理，父类 `this` 指向的是子类 `Cat` 实例**
