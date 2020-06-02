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

   >string(字符串)、list(列表)、set(集合)、sortedSet(有序集合)、hash(字典)、HyperLogLog、Geo、Pub/Sub，大家都懂，不想写一堆废话来举例。说下原理吧
   >string：int、raw、embstr
   >list：ziplist、linkedlist
   >hash：ziplist、hashtable
   >set：intset、hashtable
   >sortedSet：ziplist、skiplist+dict

7. 100万并发4G数据，10万并发400G数据，如何设计Redis存储方式？

   >[一文掌握Redis的主从复制原理到实战](https://blog.csdn.net/ctwctw/article/details/105223070)
   >[一文掌握Redis的哨兵Sentinel原理到实战](https://blog.csdn.net/ctwctw/article/details/105243302)

## redis(一)

1. 为啥用Redis

   > 因为传统的关系型数据库如Mysql已经不能适用所有的场景了，比如秒杀的库存扣减，APP首页的访问流量高峰等等，都很容易把数据库打崩，所以引入了缓存中间件，目前市面上比较常用的缓存中间件有Redis 和 Memcached 不过中和考虑了他们的优缺点，最后选择了Redis

2. Redis Model

   > BloomFilter(布隆过滤器): 防止缓存击穿
   >
   > RedisSearch:
   >
   > Redis-ML:

3. 缓存雪崩

   > 如果大量的Key过期时间过于集中，等到了过期时间点，redis可能会出现短暂的卡顿现象，严重的会出现`缓存雪崩`

   **解决：**设置过期时间时加上一个随机值，使得过期时间分散些

4. Redis分布式锁

   > 通常情况：先拿setnx来争抢锁，抢到之后，再用expire给锁加一个过期时间防止锁忘记了释放；
   >
   > 如果在setnx之后执行expire之前进程意外crash或者要重启维护了，就会导致锁永远无法释放，
   >
   > `解决:使用set命令可以同时把setnx和expire合成一条指令来用的,原子化结合起来`

5. 使用Redis做异步队列

   > 一般使用list结构作为队列，`rpush`生产消息，`lpop`消费消息。当lpop没有消息的时候，要适当sleep一会再重试；
   >
   > 如果不想使用`sleep`，list还有一个指令`blpop`，没有消息时会阻塞；

6. 生产一次，多次消费

   > 使用pub/sub主题订阅，可以实现1：N消息队列；
   >
   > 但是消费者下线情况时，生产消息会丢失，此时可以考虑消息队列；

7. Redis实现延时队列

   >使用sortedset，拿时间戳作为score，消息内容作为key调用zadd来生产消息，消费者用`zrangebyscore`指令获取N秒之前的数据轮询进行处理

8. Redis数据持久化

   > 1. RDB 做镜像全量持久化，AOF做增量持久化
   > 2. RDB持久化耗费时间较长，不实时，在突然断电或停机时会导致大量数据丢失，AOF在恢复时需要一条一条的执行写操作，也比较耗时
   > 3. 所以需要RDB和AOF结合使用，使用RDB持久化文件重新构建内存，再使用AOF重放近期的操作指令来实现完整恢复重启之前的状态
   > 4. redis本身机制是：AOF持久化开启且存在AOF文件时，优先加载AOF文件；AOF关闭或者AOF文件不存在时，加载RDB文件；加载AOF/RDB文件城后，Redis启动成功； AOF/RDB文件存在错误时，Redis启动失败并打印错误信息

9. 

10. 

11. 

12. 3

