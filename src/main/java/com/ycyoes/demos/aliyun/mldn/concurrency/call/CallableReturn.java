package com.ycyoes.demos.aliyun.mldn.concurrency.call;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//使用Callable获取线程返回值
public class CallableReturn {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new MyThread());
        new Thread(task).start();
        System.out.println("线程返回数据: " + task.get());
    }
}

class MyThread implements Callable<String> {
    public String call() {
        for (int i = 0; i < 10; i++) {
            System.out.println("*********** 线程执行， i = " + i);
        }
        return "线程执行完毕。";
    }
}