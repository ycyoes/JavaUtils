package com.ycyoes.demos.thinkinginjava.concurrency.access.lock;
//Locks in the concurrent library allow you to
//give up on trying to acquire a lock.

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author ycyoes
 * @since 2019-12-15 16:20
 * @version 1.0
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();   //true -- lock is available
        al.timed(); //true -- lock is available
        //Now create a separate task to grab the lock
        new Thread() {
            {setDaemon(true);}
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.yield(); //give the 2nd task a chance
        al.untimed();   //false -- lock grabbed by task
        al.timed();   //false -- lock grabbed by task
    }
    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLOck(): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }
}
