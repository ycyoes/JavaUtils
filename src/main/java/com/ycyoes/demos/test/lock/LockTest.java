package com.ycyoes.demos.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lock()需放到try-catch块外，防止加锁失败后解锁报错
 *
 * @author ycyoes
 * @date 2020-04-08
 */
public class LockTest {
    final static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        System.out.println("lock.");
        try {
            int i = 1 / 0;
            int a = Math.max(i, 2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("release lock!");
            lock.unlock();
        }

    }
}
