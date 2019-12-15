package com.ycyoes.demos.thinkinginjava.concurrency.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExecptionThread2());
    }
}

class HandlerThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh= " + t.getUncaughtExceptionHandler());
        return t;
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class ExecptionThread2 implements Runnable {
    public void run(){
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh= " + t.getUncaughtExceptionHandler());
    }
}
