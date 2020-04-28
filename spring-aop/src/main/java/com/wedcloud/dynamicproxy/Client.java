package com.wedcloud.dynamicproxy;

/**
 * @Author 许海斌
 *
 * @create 2020/4/16 0016 23:14
 */
public class Client {
  public static void main(String[] args) {
    // 用户租房
    // 1.房东出租房子
    Host h = new Host();
    // 2.中介 ？动态出现
    MyInvocationHandler handler = new MyInvocationHandler();
    handler.setObj(h);
    Rent o = (Rent) handler.getProxy();
    // 3.租客租到房子
    o.rent(1000);
  }
}
