package com.wedcloud.proxy;

/**
 * <h3>blog</h3>
 * <p>房东</p>
 *
 * @author : 许先生
 * @date : 2020-04-16 16:44
 **/
public class Host implements Rent {
    @Override
    public void rent() {
    System.out.println("房东实现租房接口，出租房子");
    }
}
