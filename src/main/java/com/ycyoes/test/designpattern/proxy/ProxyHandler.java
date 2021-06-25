package com.ycyoes.test.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler<T> implements InvocationHandler {
    private T target;

    public ProxyHandler(T target) {
        this.target = target;
    }

    /**
     * 代理方法
     * @param proxy 代理对象
     * @param method    代理方法
     * @param args  代理方法的参数
     * @return  方法执行结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---------dynaminc proxy-------------");
        System.out.println("pre words");
        Object ans = method.invoke(target, args);
        System.out.println("post words");
        return ans;
    }
}
