package com.ycyoes.concurrent.jdk.chapter1;

public class PrintCircle {
    public static void main(String[] args) {
        System.out.println("main enter!");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        System.out.println("executing");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        System.out.println("main exit！");
    }
}
