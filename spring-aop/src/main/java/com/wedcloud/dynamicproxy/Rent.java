package com.wedcloud.dynamicproxy;

/**
 * @Author 许海斌
 *
 * @create 2020/4/16 0016 23:12
 */
public interface Rent {
  /**
   * 租房
   *
   * @param money
   */
  void rent(int money);

  /** 消息通知 */
  void fun();
}
