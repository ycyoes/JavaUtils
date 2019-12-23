package com.ycyoes.demos.interview.test;

public class ThreadTest {
    public static void main(String[] args) {
        new Thread(() -> {new ThreadA().run();}).start();
        new Thread(() -> {new ThreadB().run();}).start();
        new Thread(() -> {new ThreadC().run();}).start();
    }
}

class ThreadA implements Runnable {
    public void run() {
        while (true) {
            System.out.println("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}

class ThreadB implements Runnable {
    public void run() {
        while (true) {
            System.out.println("B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}

class ThreadC implements Runnable {
    public void run() {
        while (true) {
            System.out.println("C");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}
