package com.ycyoes.demos.interview.test.circle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//创建三个线程，循环打印A、B、C，输出ABC
public class PrintByLock {
    private static Lock lock = new ReentrantLock();
    private static int status = 0;

    public static void main(String[] args) {
        new PrintByLock().printABC();
    }

    public void printABC() {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        //线程的执行顺序跟提交顺序无关
        exec.execute(new RunnableC());
        exec.execute(new RunnableB());
        exec.execute(new RunnableA());
        exec.shutdownNow();
    }

    private class RunnableA implements Runnable {
        public void run() {
            while(true) {
                try {
                    lock.lock();
                    while (status % 3 == 0) {
                        System.out.println("A");
                        status++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class RunnableB implements Runnable {
        public void run() {
            while(true) {
                try {
                    lock.lock();
                    while (status % 3 == 1) {
                        System.out.println("B");
                        status++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class RunnableC implements Runnable {
        public void run() {
            while(true) {
                try {
                    lock.lock();
                    while (status % 3 == 2) {
                        System.out.println("C");
                        status++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}


