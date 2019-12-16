package com.ycyoes.demos.thinkinginjava.concurrency.access.practise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Modify Exercise 15 to use explicit Lock objects.
public class Ex16 {
    public static void main(String[] args) {
        final SyncTest3 st1 = new SyncTest3();
        final SyncTest4 st2 = new SyncTest4();
        new Thread(() -> {
            st1.f1();
        }).start();
        new Thread(() -> {
            st1.g1();
        }).start();
        new Thread(() -> {
            st1.h1();
        }).start();
        new Thread(() -> {
            st2.f2();
        }).start();
        new Thread(() -> {
            st2.g2();
        }).start();
        new Thread(() -> {
            st2.h2();
        }).start();
    }
}

//each method has a different lock
class SyncTest4 {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private Lock lock3 = new ReentrantLock();
    public void f2() {
        lock1.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("f2()");
                Thread.yield();
            }
        } finally {
            lock1.unlock();
        }
    }
    public void g2() {
        lock2.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("g2()");
                Thread.yield();
            }
        } finally {
            lock2.unlock();
        }
    }
    public void h2() {
        lock3.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("h2()");
                Thread.yield();
            }
        } finally {
            lock3.unlock();
        }
    }
}

//all methods synchronized on lock
class SyncTest3 {
    private Lock lock = new ReentrantLock();
    public void f1() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("f1()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }
    public void g1() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("g1()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }
    public void h1() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("h1()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }
}
