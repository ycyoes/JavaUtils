package com.ycyoes.demos.thinkinginjava.concurrency.task;

/**
 * Adding more threads.
 * @author ycyoes
 * @since 2019-12-13 10:36
 * @version 1.0
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff.");
    }
}
