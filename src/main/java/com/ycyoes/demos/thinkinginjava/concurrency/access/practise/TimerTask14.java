package com.ycyoes.demos.thinkinginjava.concurrency.access.practise;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimerTask14 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++)
            exec.execute(new SimpleTask());
        exec.shutdown();
    }
}

class SimpleTask implements Runnable{
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("-------timer task---------");
            }
        }, 100);
    }
}
