package com.ycyoes.demos.concurrency.deep.chapter1;

import java.util.concurrent.TimeUnit;

public class WaitingTime implements Runnable {
    @Override
    public void run() {
        while (true) {

        }
    }

    public static final void waitSecond(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
