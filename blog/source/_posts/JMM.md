---
title: JMM
date: 2020-04-03 13:44:48
tags: [Java,并发]
categories: 
- JMM
---

## JMM内存模型
!["JMM"](/source/image/111.jpg "JMM")

## 内存交互操作
内存交互操作有8种，虚拟机实现必须保证每一个操作都是原子的，不可在分的（对于double和long类型的变量来说，load、store、read和write操作在某些平台上允许例外）

* lock     （锁定）：作用于主内存的变量，把一个变量标识为线程独占状态
* unlock （解锁）：作用于主内存的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定
* read    （读取）：作用于主内存变量，它把一个变量的值从主内存传输到线程的工作内存中，以便随后的load动作使用
* load     （载入）：作用于工作内存的变量，它把read操作从主存中变量放入工作内存中
* use      （使用）：作用于工作内存中的变量，它把工作内存中的变量传输给执行引擎，每当虚拟机遇到一个需要使用到变量的值，就会使用到这个指令
* assign  （赋值）：作用于工作内存中的变量，它把一个从执行引擎中接受到的值放入工作内存的变量副本中
* store    （存储）：作用于主内存中的变量，它把一个从工作内存中一个变量的值传送到主内存中，以便后续的write使用
* write 　（写入）：作用于主内存中的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中


　　JMM对这八种指令的使用，制定了如下规则：

* 不允许read和load、store和write操作之一单独出现。即使用了read必须load，使用了store必须write
* 不允许线程丢弃他最近的assign操作，即工作变量的数据改变了之后，必须告知主存
* 不允许一个线程将没有assign的数据从工作内存同步回主内存
* 一个新的变量必须在主内存中诞生，不允许工作内存直接使用一个未被初始化的变量。就是怼变量实施use、store操作之前，必须经过assign和load操作
* 一个变量同一时间只有一个线程能对其进行lock。多次lock后，必须执行相同次数的unlock才能解锁
* 如果对一个变量进行lock操作，会清空所有工作内存中此变量的值，在执行引擎使用这个变量前，必须重新load或assign操作初始化变量的值
* 如果一个变量没有被lock，就不能对其进行unlock操作。也不能unlock一个被其他线程锁住的变量
* 对一个变量进行unlock操作之前，必须把此变量同步回主内存

## 模型特征
　　**原子性**：例如上面八项操作，在操作系统里面是不可分割的单元。被synchronized关键字或其他锁包裹起来的操作也可以认为是原子的。从一个线程观察另外一个线程的时候，看到的都是一个个原子性的操作。

```
1         synchronized (this) {
2             a=1;
3             b=2;
4         }
```
　　例如一个线程观察另外一个线程执行上面的代码，只能看到a、b都被赋值成功结果，或者a、b都尚未被赋值的结果。

　　**可见性**：每个工作线程都有自己的工作内存，所以当某个线程修改完某个变量之后，在其他的线程中，未必能观察到该变量已经被修改。volatile关键字要求被修改之后的变量要求立即更新到主内存，每次使用前从主内存处进行读取。因此volatile可以保证可见性。除了volatile以外，synchronized和final也能实现可见性。synchronized保证unlock之前必须先把变量刷新回主内存。final修饰的字段在构造器中一旦完成初始化，并且构造器没有this逸出，那么其他线程就能看到final字段的值。

　　**有序性**：java的有序性跟线程相关。如果在线程内部观察，会发现当前线程的一切操作都是有序的。如果在线程的外部来观察的话，会发现线程的所有操作都是无序的。因为JMM的工作内存和主内存之间存在延迟，而且java会对一些指令进行重新排序。volatile和synchronized可以保证程序的有序性，很多程序员只理解这两个关键字的执行互斥，而没有很好的理解到volatile和synchronized也能保证指令不进行重排序

## Final域的内存语义
　　被final修饰的变量，相比普通变量，内存语义有一些不同。具体如下：

JMM禁止把Final域的写重排序到构造器的外部。
在一个线程中，初次读该对象和读该对象下的Final域，JMM禁止处理器重新排序这两个操作。
```
 1 public class FinalConstructor {
 2 
 3     final int a;
 4 
 5     int b;
 6 
 7     static FinalConstructor finalConstructor;
 8 
 9     public FinalConstructor() {
10         a = 1;
11         b = 2;
12     }
13 
14     public static void write() {
15         finalConstructor = new FinalConstructor();
16     }
17 
18     public static void read() {
19         FinalConstructor constructor = finalConstructor;
20         int A = constructor.a;
21         int B = constructor.b;
22     }
23 }
```
　　假设现在有线程A执行FinalConstructor.write()方法，线程B执行FinalConstructor.read()方法。

　　对应上述的Final的第一条规则，因为JMM禁止把Final域的写重排序到构造器的外部，而对普通变量没有这种限制，所以变量A=1，而变量B可能会等于2（构造完成），也有可能等于0（第11行代码被重排序到构造器的外部）。

 　　对应上述的Final的第二条规则，如果constructor的引用不为null，A必然为1，要么constructor为null，抛出空指针异常。保证读final域之前，一定会先读该对象的引用。但是普通对象就没有这种规则


 ## Happen-Before（先行发生规则）
　　在常规的开发中，如果我们通过上述规则来分析一个并发程序是否安全，估计脑壳会很疼。因为更多时候，我们是分析一个并发程序是否安全，其实都依赖Happen-Before原则进行分析。Happen-Before被翻译成先行发生原则，意思就是当A操作先行发生于B操作，则在发生B操作的时候，操作A产生的影响能被B观察到，“影响”包括修改了内存中的共享变量的值、发送了消息、调用了方法等。

### Happen-Before的规则有以下几条

* 程序次序规则（Program Order Rule）：在一个线程内，程序的执行规则跟程序的书写规则是一致的，从上往下执行。

* 管程锁定规则（Monitor Lock Rule）：一个Unlock的操作肯定先于下一次Lock的操作。这里必须是同一个锁。同理我们可以认为在synchronized同步同一个锁的时候，锁内先行执行的代码，对后续同步该锁的线程来说是完全可见的。
* volatile变量规则（volatile Variable Rule）：对同一个volatile的变量，先行发生的写操作，肯定早于后续发生的读操作
* 线程启动规则（Thread Start Rule）：Thread对象的start()方法先行发生于此线程的没一个动作
* 线程中止规则（Thread Termination Rule）：Thread对象的中止检测（如：Thread.join()，Thread.isAlive()等）操作，必行晚于线程中所有操作
* 线程中断规则（Thread Interruption Rule）：对线程的interruption（）调用，先于被调用的线程检测中断事件(Thread.interrupted())的发生
* 对象中止规则（Finalizer Rule）：一个对象的初始化方法先于一个方法执行Finalizer()方法
* 传递性（Transitivity）：如果操作A先于操作B、操作B先于操作C,则操作A先于操作C


以上就是Happen-Before中的规则。通过这些条件的判定，仍然很难判断一个线程是否能安全执行，毕竟在我们的时候线程安全多数依赖于工具类的安全性来保证。想提高自己对线程是否安全的判断能力，必然需要理解所使用的框架或者工具的实现，并积累线程安全的经验