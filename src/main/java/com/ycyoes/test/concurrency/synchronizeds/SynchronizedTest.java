package com.ycyoes.test.concurrency.synchronizeds;

public class SynchronizedTest {
    public synchronized void test1() {

    }

    public void test2() {
        synchronized (this) {

        }
    }
}
