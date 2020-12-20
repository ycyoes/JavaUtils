package com.ycyoes.demos.geetime.concurrent.practise.chapter03;

/**
 * synchronized
 * @author ycyoes
 * @date 2020-12-20 15:14
 */
public class SafeCalc {
    long value = 0L;
    synchronized long get() {
        return value;
    }
    synchronized void addOne() {
        value += 1;
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCalc sc = new SafeCalc();
        System.out.println("before add: " + sc.get());
        sc.addOne();
        System.out.println("after add: " + sc.get());
        Thread t = new Thread(() -> {
            sc.addOne();
        });
        Thread.sleep(10000);
        for (int i = 0; i < 5; i++) {
            sc.addOne();
        }
        System.out.println(sc.get());
    }
}
