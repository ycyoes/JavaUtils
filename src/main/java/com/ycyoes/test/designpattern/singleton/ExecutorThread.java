package com.ycyoes.test.designpattern.singleton;

public class ExecutorThread implements Runnable {

    @Override
    public void run() {
        LazySimpleSingleton singleton = LazySimpleSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + singleton);
    }
}
