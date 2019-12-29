package com.ycyoes.demos.aliyun.mldn.test.singleton;

//使用内部类来做到延迟加载对象，在初始化这个内部类的时候JLS(Java Language Specification)会保证这个类的线程安全,
//这种写法优美之处在于，完全使用了Java虚拟机的机制进行同步保证，没有一个同步的关键字。
public class Singleton {
    private static class SingletonHolder{
        public final static Singleton instance = new Singleton();
    }
    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
