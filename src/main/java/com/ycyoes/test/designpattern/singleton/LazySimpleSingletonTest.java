package com.ycyoes.test.designpattern.singleton;

/**
 * 懒汉式单例测试
 *
 * 多次执行，获取的对象可能不一致
 */
public class LazySimpleSingletonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ExecutorThread());
        Thread t2 = new Thread(new ExecutorThread());
        t1.start();
        t2.start();
        System.out.println("end");

    }
}
