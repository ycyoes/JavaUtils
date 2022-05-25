package com.ycyoes.demos.concurrency.core.chapter1;

public class CreateDemo {
    public static final int MAX_TURN = 5;
    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    //线程的编号
    static int threadNo = 1;

    public static void main(String[] args) {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            thread = new DemoThread();
            thread.start();
        }
    }

    static class DemoThread extends Thread {
        public DemoThread() {
            super("DemoThread-" + threadNo++);
        }

        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(getName() + ", 轮次： " + i);
            }
            System.out.println(getName() + " 运行结束");
        }
    }
}
