package com.ycyoes.test.designpattern.singleton;

//懒汉式单例在外部需要使用的时候才进行实例化
public class LazySimpleSingleton {
    private LazySimpleSingleton() {}
    //静态块，公共内存区域
    private static LazySimpleSingleton lazy = null;
    public static synchronized LazySimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }
}
