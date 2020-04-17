package com.wedcloud.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author 许海斌
 * @create 2020/4/16 0016 23:16
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object obj;

    /**
     * 代理角色获得需要代理的真实角色
     * @param obj
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * 产生并返回代理角色实例
     * @return
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }


    /**
     * 代理（中介）调用被代理角色的处理程序
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     * 方法区内可以插入公共业务或附属方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = method.invoke(obj,args);
        return o;
    }
}
