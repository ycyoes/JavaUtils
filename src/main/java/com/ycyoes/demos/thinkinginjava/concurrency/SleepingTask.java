package com.ycyoes.demos.thinkinginjava.concurrency;
//Calling sleep() to pause for a while.

import com.ycyoes.demos.thinkinginjava.concurrency.task.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.println(status());
            }
            //之前写法
            //Thread.sleep(100);
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
}
