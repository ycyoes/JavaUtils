package com.ycyoes.demos.aliyun.mldn.concurrency.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//设置三个抢答者（三个线程），而后同事发出抢答指令，抢答成功者给出成功提示，
//未抢答成功者给出失败提示
public class RaceAnswer {
    public static void main(String[] args) throws Exception {
        MyThread mt = new MyThread();
        FutureTask<String> taskA = new FutureTask<String>(mt);
        FutureTask<String> taskB = new FutureTask<String>(mt);
        FutureTask<String> taskC = new FutureTask<String>(mt);
        new Thread(taskA, "竞赛者A").start();
        new Thread(taskB, "竞赛者B").start();
        new Thread(taskC, "竞赛者C").start();
        System.out.println(taskA.get());
        System.out.println(taskB.get());
        System.out.println(taskC.get());
    }
}

class MyThread implements Callable<String> {
    private boolean flag = false;

    @Override
    public String call() throws Exception {
        synchronized (this) {
            if (!this.flag) {
                this.flag = true;
                return Thread.currentThread().getName() + "抢答成功";
            } else {
                return Thread.currentThread().getName() + "抢答失败";
            }
        }
    }
}