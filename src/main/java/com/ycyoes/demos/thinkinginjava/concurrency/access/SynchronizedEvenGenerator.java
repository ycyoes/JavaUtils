package com.ycyoes.demos.thinkinginjava.concurrency.access;

//Simplifying mutexes with the synchronized keyword.
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    public synchronized int next() {
        ++currentEvenValue;
        System.out.println(Thread.currentThread() + "---------1-------" + currentEvenValue);
        Thread.yield(); //Cause failure faster
        ++currentEvenValue;
        System.out.println(Thread.currentThread() + "---------2-------" + currentEvenValue);
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
