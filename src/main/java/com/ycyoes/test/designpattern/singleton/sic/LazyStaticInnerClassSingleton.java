package com.ycyoes.test.designpattern.singleton.sic;

/**
 * 这种形式兼顾饿汉式单例写法的内存浪费问题和synchronized的性能问题
 * 完美的屏蔽了这两个缺点
 */
public class LazyStaticInnerClassSingleton {
    //使用LazyStaticInnerClassSingleton的时候，默认会先初始化内部类
    //如果没使用，则内部类是不加载的
    private LazyStaticInnerClassSingleton() {
        //在构造方法中抛出异常，显然不够优雅
        if (LazyHolder.INSTANCE != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    //每一个关键字都不是多余的，static是为了使单例模式的空间共享，保证这个方法不会被重写、重载
    private static LazyStaticInnerClassSingleton getInstance() {
        //在返回结果之前，一定会先加载内部类
        return LazyHolder.INSTANCE;
    }

    //利用了Java本身的语法特点，默认不加载内部类
    private static class LazyHolder {
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }
}
