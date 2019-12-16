package com.ycyoes.demos.thinkinginjava.concurrency.end.block;

//One thread can reacquire the same.
public class MultiLock {
    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread(() -> {
            System.out.println("main: " + Thread.currentThread().getName());
            multiLock.f1(10);
        }).start();
    }
    public synchronized void f1(int count) {
        System.out.println("f1: " + Thread.currentThread().getName());
        if (count-- > 0){
            System.out.println("f1() call f2() with count " + count);
            f2(count);
        }
    }
    public synchronized void f2(int count) {
        System.out.println("f2: " + Thread.currentThread().getName());
        if (count-- > 0){
            System.out.println("f2() call f1() with count " + count);
            f1(count);
        }
    }
}
