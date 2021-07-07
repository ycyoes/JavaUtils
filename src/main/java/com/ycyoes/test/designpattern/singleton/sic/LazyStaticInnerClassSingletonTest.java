package com.ycyoes.test.designpattern.singleton.sic;

import java.lang.reflect.Constructor;

public class LazyStaticInnerClassSingletonTest {
    public static void main(String[] args) {
        try {
            //如果有人恶意用反射破坏
            Class<?> clazz = LazyStaticInnerClassSingleton.class;

            //通过反射获取私有的构造方法
            Constructor c = clazz.getDeclaredConstructor(null);
            //强制访问
            c.setAccessible(true);
            //暴力初始化
            Object o1 = c.newInstance();
            //调用了两次构造方法，相当于new了两次，犯了原则性错误
            Object o2 = c.newInstance();

            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
