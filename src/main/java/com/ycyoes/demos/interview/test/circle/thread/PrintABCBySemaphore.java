package com.ycyoes.demos.interview.test.circle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PrintABCBySemaphore {
    //信号量,表示可用的许可数量，用于控制同时容许访问的线程数量
    public Semaphore s1 = new Semaphore(1);
    public Semaphore s2 = new Semaphore(0);
    public Semaphore s3 = new Semaphore(0);

    public static void main(String[] args) {
        new PrintABCBySemaphore().printABC();
    }

    public void printABC() {
        ExecutorService exe = Executors.newFixedThreadPool(3);
        exe.execute(new RunnableC());
        exe.execute(new RunnableA());
        exe.execute(new RunnableB());
        exe.shutdown();
    }

    private class RunnableA implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    s1.acquire();//获取信号量，s1 - 1
                    System.out.println("A");
                    s2.release();//释放信号量，s2 + 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class RunnableB implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    s2.acquire();//获取信号量，s2 - 1
                    System.out.println("B");
                    s3.release();//释放信号量，s3 + 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class RunnableC implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    s3.acquire();//获取信号量，s3 - 1
                    System.out.println("C");
                    s1.release();//释放信号量，s1 + 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
