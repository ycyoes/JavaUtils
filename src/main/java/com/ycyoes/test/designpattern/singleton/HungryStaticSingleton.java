package com.ycyoes.test.designpattern.singleton;

/**
 * 饿汉式静态块单例模式
 *
 * 饿汉式单例写法在类加载的时候立即初始化，并且创建单例对象。
 * 它绝对线程安全，在线程还没出现之前就实例化了，不可能存在访问安全问题。
 */

public class HungryStaticSingleton {
    private static final HungryStaticSingleton hungrySingleton;
    static {
        hungrySingleton = new HungryStaticSingleton();
    }
    private HungryStaticSingleton() {}
    public static HungryStaticSingleton getInstance() {
        return hungrySingleton;
    }
}
