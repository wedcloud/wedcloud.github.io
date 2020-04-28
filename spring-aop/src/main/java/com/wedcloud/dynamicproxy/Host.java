package com.wedcloud.dynamicproxy;

/**
 * @Author 许海斌
 *
 * @create 2020/4/16 0016 23:13
 */
public class Host implements Rent {
  private static final int m = 1000;

  @Override
  public void rent(int money) {
    if (money > m) {
      System.out.println("房东出租房子");
    } else {
      System.out.println("房东不出租房子");
    }
  }

  @Override
  public void fun() {
    System.out.println("停水。。停电");
  }
}
