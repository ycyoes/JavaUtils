package com.ycyoes.demos.interview.test.circle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCByLockAndCondition {
    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();
    private static int status = 0;

    public static void main(String[] args) {
        new PrintABCByLockAndCondition().printABC();
    }

    public void printABC() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        //线程的执行顺序跟提交顺序无关
        service.execute(new RunnableC());
        service.execute(new RunnableB());
        service.execute(new RunnableA());
        service.shutdown();
    }

    private class RunnableA implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                while (true) {
                    while (status % 3 != 0) {//注意这里是不等于0，也就是说没轮到该线程执行，之前一直等待状态
                        conditionA.await();//释放了锁，进入阻塞等待，依赖C唤醒
                    }
                    System.out.println("A");
                    status++;
                    conditionB.signal(); // A执行完唤醒B线程
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    private class RunnableB implements Runnable {
        @Override
        public void run() {

            try {
                lock.lock();
                while (true) {
                    while (status % 3 != 1) {
                        conditionB.await();
                    }
                    System.out.println("B");
                    status++;
                    conditionC.signal(); // B执行完唤醒C线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    private class RunnableC implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                while (true) {
                    while (status % 3 != 2) {
                        conditionC.await();
                    }
                    System.out.println("C");
                    status++;
                    conditionA.signal(); // C执行完唤醒A线程
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
