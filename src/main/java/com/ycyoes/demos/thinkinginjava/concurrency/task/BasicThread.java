package com.ycyoes.demos.thinkinginjava.concurrency.task;

/**
 * The most basic use of the Thread class.
 * @author ycyoes
 * @since 2019-12-13 10:06
 * @version 1.0
 */
public class BasicThread {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff.");
    }
}
