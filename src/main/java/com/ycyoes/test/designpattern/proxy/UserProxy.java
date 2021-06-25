package com.ycyoes.test.designpattern.proxy;

public class UserProxy implements UserInterface {
    private UserInterface target;

    public UserProxy(UserInterface target) {
        this.target = target;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("pre words");
        target.sayHello(name);
        System.out.println("post words");
        return name;
    }

    public static void main(String[] args) {
        //生成被代理对象
        User user = new User();

        //生成代理，顺便告诉代理它要代理谁
        UserProxy userProxy = new UserProxy(user);

        //触发代理方法
        userProxy.sayHello("ycyoes");
    }
}
