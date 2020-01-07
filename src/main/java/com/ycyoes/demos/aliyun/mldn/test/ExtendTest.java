package com.ycyoes.demos.aliyun.mldn.test;

public class ExtendTest {
    private static final String nickName = "niki";
    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(son.getName());
//        nickName = "change";      //无法为final变量重新赋值
    }
}

class Father {
    private static final String name = "father";
    public static String getName() {
        return name;
    }
}

class Son extends Father {
    private static final String name = "son";
}