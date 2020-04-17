package com.wedcloud.springapiaop.service.impl;

import com.wedcloud.springapiaop.service.UserService;

/**
 * <h3>blog</h3>
 * <p>用户实现类</p>
 *
 * @author : 许先生
 * @date : 2020-04-17 13:35
 **/
public class UserServiceImpl implements UserService {

    @Override
    public int add() {
        System.out.println("add");
        return 1;
    }

    @Override
    public void modify() {
        System.out.println("modify");
    }

    @Override
    public void del() {
        System.out.println("del");
    }

    @Override
    public void select() {
        System.out.println("select");
    }
}
