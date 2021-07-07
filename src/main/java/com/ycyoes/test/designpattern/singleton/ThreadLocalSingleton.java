package com.ycyoes.test.designpattern.singleton;

/**
 * ThreadLocal不能保证其创建的对象是全局唯一的，但能保证在单个线程中是唯一的，是线程安全的
 *
 * 单例模式为了达到线程安全的目的，会给方法上锁，以时间换空间。
 * ThreadLocal将所有对象全部放在ThreadLocalMap中，为每个线程都提供一个对象，实际上是以空间换时间来实现线程隔离的。
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = ThreadLocal.withInitial(() -> new ThreadLocalSingleton());
    private ThreadLocalSingleton() {}
    public static ThreadLocalSingleton getInstance() {
        return threadLocalInstance.get();
    }
}
