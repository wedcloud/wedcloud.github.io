---
title: 面试-redis
tags: [面试]
categories:
  - 面试
  - redis
date: 2020-05-06 09:23:12
---

## redis

1. Redis为什么是key，value的，为什么不是支持SQL的？

   >redis的内存模型是一个hashtable，不使用表来存储数据，也不会预定义或强制要求用户对redis储存的不同数据进行关联。

2. Redis是多线程还是单线程？

   >redis中io多路复用器模块是单线程执行，事件处理器也是单线程执行，两个线程不一样。所以实际redis应该是单进程多线程，只是不同的模块都用的单线程实现。
   >两个维度来举例：
   >（1）若是client发送命令到server的话，server处理命令是单线程逐条进行的。
   >（2）server内部可以是多线程的，比如aof持久化，假设策略每秒，那就是再单独开启一个线程去执行aof文件持久化操作，这就是多线程了

3. Redis的持久化开启了RDB和AOF下重启服务是如何加载的？

   > 优先AOF，AOF没找到的话再找RDB，因为AOF文件的数据要全于RDB

   [彻底搞懂Redis持久化之RDB原理](https://blog.csdn.net/ctwctw/article/details/105147277)
   [彻底搞懂Redis持久化之AOF原理](https://blog.csdn.net/ctwctw/article/details/105173842)
   [Redis持久化之RDB与AOF对比总结](https://blog.csdn.net/ctwctw/article/details/105180199)

4. Redis如果做集群该如何规划？AKF/CAP如何实现和设计？

   >参考redis各种部署方式的优缺点来决定。
   >如果希望快速部署，那么可以考虑单节点部署方式。
   >如果只需要考虑可靠性，那么可以考虑主从复制模式。
   >如果想要保证高可用，不需要考虑储存成本可以考虑哨兵模式。
   >如果想提高集群的扩展性和可用性，不要求保证数据的强一致性，且没有批量操作，那么可以考虑集群模式

   [大白话图文结合的方式讲解什么是CAP](https://blog.csdn.net/ctwctw/article/details/105197418)

5. 10万用户一年365天的登录情况如何用redis存储，并快速检索任意时间窗内的活跃用户？

   >[Redis的bitmap从基础到业务](https://blog.csdn.net/ctwctw/article/details/105013817)

6. Redis的5种Value类型你用过几种，能举例吗？

   >string、list、set、sorted_set、hash，大家都懂，不想写一堆废话来举例。说下原理吧
   >string：int、raw、embstr
   >list：ziplist、linkedlist
   >hash：ziplist、hashtable
   >set：intset、hashtable
   >sorted set：ziplist、skiplist+dict

7. 100万并发4G数据，10万并发400G数据，如何设计Redis存储方式？

   >[一文掌握Redis的主从复制原理到实战](https://blog.csdn.net/ctwctw/article/details/105223070)
   >[一文掌握Redis的哨兵Sentinel原理到实战](https://blog.csdn.net/ctwctw/article/details/105243302)

8. 3

9. 3

10. 3

11. 3

