package com.opensource.mybatis;


public class Test {

    public static void main(String[] args) {
       ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        System.out.println(classloader);
        ClassLoader classloader2 = new Test().getClassLoader();
        System.out.println(classloader2);

    }

    public ClassLoader getClassLoader(){
        return getClass().getClassLoader();
    }
}
