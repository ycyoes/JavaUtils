package com.ycyoes.demos.thinkinginjava.concurrency.access.lock;

import com.ycyoes.demos.thinkinginjava.concurrency.access.EvenChecker;
import com.ycyoes.demos.thinkinginjava.concurrency.access.IntGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Preventing thread collisions with mutexes.
/**
 * 互斥锁
 * @author ycyoes
 * @since 2019-12-15 16:52
 * @version 1.0
 */
public class MutexEventGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();
    public int next() {
        try {
            lock.lock();
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEventGenerator());
    }
}
