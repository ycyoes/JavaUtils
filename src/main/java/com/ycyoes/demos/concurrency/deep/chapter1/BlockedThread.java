package com.ycyoes.demos.concurrency.deep.chapter1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockedThread {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            es.execute(new BlockedTarget());
        }

    }

    static class BlockedTarget implements Runnable {

        @Override
        public void run() {
            synchronized (BlockedTarget.class) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    WaitingTime.waitSecond(100);
                }
            }
        }
    }
}
