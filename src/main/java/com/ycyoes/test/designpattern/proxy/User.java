package com.ycyoes.test.designpattern.proxy;

public class User implements UserInterface {
    @Override
    public String sayHello(String name) {
        System.out.println("hello " + name);
        return "OK";
    }
}
