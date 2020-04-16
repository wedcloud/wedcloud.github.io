package com.wedcloud.proxy;

/**
 * <h3>blog</h3>
 * <p>中介</p>
 *
 * @author : 许先生
 * @date : 2020-04-16 16:49
 **/
public class Proxy implements Rent{

    private Host host;
    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
    System.out.println("中介");
    look();
    buy();
    free();
    host.rent();
    }

    /**
     * 公共业务
     */
    public void free(){
        System.out.println("服务费");
    }
    public void look(){
        System.out.println("看房");
    }
    public void buy(){
        System.out.println("签合同");
    }
}
