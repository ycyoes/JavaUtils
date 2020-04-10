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
    private int count = 0;
    public Runnable run1 = new Runnable() {
        public void run() {
            //加锁
            lock.lock();
            while (count < 1000) {
                try {
                    //打印是否执行该方法
                    System.out.println(Thread.currentThread().getName() + "run1:" + count++);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    };
    //方法2
    public Runnable run2 = new Runnable() {
        public void run() {
            lock.lock();
            while(count<1000){
                try {
                    System.out.println(Thread.currentThread().getName()+"run2"+count++);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    };

    public static void main(String[] args) {
//        lockTry();
        //创建一个对象
        LockTest s = new LockTest();
        //获取该对象的方法1
        new Thread(s.run1).start();
        //获取该对象的方法2
        new Thread(s.run2).start();

    }

    private static void lockTry() {
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
