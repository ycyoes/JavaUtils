package com.ycyoes.demos.concurrency.core.chapter1;

public class CreateDemo2 {
    public static final int MAX_TURN = 5;
    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    //线程的编号
    static int threadNo = 1;

    public static void main(String[] args) {
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            Runnable target = new RunTarget();
            thread = new Thread(target, "RunnableThread" + threadNo++);
            thread.start();
        }
    }

    static class RunTarget implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(getCurThreadName() + ", 轮次： " + i);
            }
            System.out.println(getCurThreadName() + " 运行结束");
        }
    }
}
