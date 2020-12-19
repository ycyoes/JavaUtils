package com.ycyoes.demos.geetime.concurrent.practise.chapter01;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * join()的使用
 */
public class JoinTest implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new JoinTest());
        long start = System.currentTimeMillis();
        t.start();
        t.join(1000);   //等待线程t 1s
        System.out.println("时间间隔: " + (System.currentTimeMillis() - start));
        System.out.println("Main finished");    //打印主线程结束
    }

    @Override
    public void run() {
        synchronized (currentThread()) {
            for (int i = 0; i < 5; i++) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("睡眠" + i);
            }
            System.out.println("JoinTest finished");   //t线程结束
        }
    }
}
