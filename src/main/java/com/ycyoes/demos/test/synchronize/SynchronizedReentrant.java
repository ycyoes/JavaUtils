package com.ycyoes.demos.test.synchronize;

/**
 * Synchronized是可重入锁
 *
 * 可重入锁的原理是什么？
 *
 * 重入锁实现可重入性原理或机制是：
 * 每一个锁关联一个线程持有者和计数器，当计数器为 0 时表示该锁没有被任何线程持有，那么任何线程都可能获得该锁而调用相应的方法；
 * 当某一线程请求成功后，JVM会记下锁的持有线程，并且将计数器置为 1；此时其它线程请求该锁，则必须等待；
 * 而该持有锁的线程如果再次请求这个锁，就可以再次拿到这个锁，同时计数器会递增；
 * 当线程退出同步代码块时，计数器会递减，如果计数器为 0，则释放该锁。
 *
 * @author ycyoes
 */
public class SynchronizedReentrant extends SuperSynchronizedReentrant{

    public static void main(String[] args) {
        SynchronizedReentrant synchronizedReentrant = new SynchronizedReentrant();
        synchronizedReentrant.doSomething();
    }

    public synchronized void doSomething() {
        System.out.println("child.doSomething(): " + Thread.currentThread().getName());
        doAnotherThing();   //调用自己类中其他的synchronized方法
    }

    public synchronized void doAnotherThing() {
        super.doSomething();    //调用父类synchronized方法
        System.out.println("child.doAnotherThing(): " + Thread.currentThread().getName());
    }
}

class SuperSynchronizedReentrant {
    public synchronized void doSomething() {
        System.out.println("father.doSomething(): " + Thread.currentThread().getName());
    }
}
