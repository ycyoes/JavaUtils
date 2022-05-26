package com.ycyoes.demos.concurrency.deep.chapter1;

public class WaitingState implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            new WaitingState();
        }).start();
        Thread.sleep(1000000);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (WaitingState.class) {
                try {
                    WaitingState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
