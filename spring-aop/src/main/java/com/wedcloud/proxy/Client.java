package com.wedcloud.proxy;

/**
 * <h3>blog</h3>
 * <p>客户</p>
 *
 * @author : 许先生
 * @date : 2020-04-16 16:45
 **/
public class Client {
  public static void main(String[] args) {
    // 租客直接找到房东租房
//    Host h = new Host();
//    h.rent();
    // 租客找不到房东，找中介（代理房东）
    Host h = new Host();
    Proxy p = new Proxy(h);
    p.rent();
  }
}
