package com.ycyoes.demos.thinkinginjava.concurrency.daemon;

import java.util.concurrent.TimeUnit;

//Daemon threads don't run the finally clause
public class DaemonDontRunFinally {
    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}

class ADaemon implements Runnable {
    public void run() {
        try {
            System.out.println("Starting ADaemon.");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException.");
        } finally {
            System.out.println("This should always run?");
        }
    }
}
