package com.ycyoes.demos.thinkinginjava.concurrency.access.atomic;

//Operations that may seem safe are not, when threads are present.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Reuses storage so we don't run out of memory
 * @author ycyoes
 * @since 2019-12-15 17:41
 * @version 1.0
 */
public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable {
        public void run(){
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++)
            exec.execute(new SerialChecker());
        //Stop after n seconds if there's an argument:
        if (args.length > 0) {
            TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
            System.out.println("No duplicates detected.");
            System.exit(0);
        }
    }
}

class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;
    public CircularSet(int size) {
        array = new int[size];
        len = size;
        //Initialize to a value not produced
        //by the SerialNumberGenerator
        for(int i = 0; i < size; i++)
            array[i] = -1;
    }
    public synchronized void add(int i) {
        array[index] = i;
        //Wrap index and write over old elements:
        index = ++index % len;
    }
    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++)
            if (array[i] == val) return true;
            return false;
    }
}
