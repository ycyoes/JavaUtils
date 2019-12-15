package com.ycyoes.demos.thinkinginjava.concurrency.access.practise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumRangeGenerator11 extends NumRangeGenerator {
    private int min = 0;
    private int max = 0;
    private int[] range = {min, max};
    private Random rand = new Random();
    public int[] next() {
        min = rand.nextInt(100);
        max = rand.nextInt(100);
        Thread.yield();
        if (min > max) max = min;
        int[] ia = {min, max};
        return ia;
    }

    public static void main(String[] args) {
        NumRangeCheck11.test(new NumRangeGenerator11());
    }
}
//task(s) that depend on & share NumRangeGenerator
class NumRangeCheck11 implements Runnable {
    private NumRangeGenerator generator;
    private final int id;
    public NumRangeCheck11(NumRangeGenerator g, int ident) {
        generator = g;
        id = ident;
    }
    public void run() {
        System.out.println("Testing...");
        while (!generator.isCanceled()) {
            int[] range = generator.next();
            if (range[0] > range[1]) {
                System.out.println("Error in test#" + id + ": min " + range[0]
                        + " > " + "max " + range[1]);
                generator.cancel();
            }
        }
    }
    public static void test(NumRangeGenerator gen, int count){
        System.out.println("Press Ctrl-C to exit.");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new NumRangeCheck11(gen, i));
        }
        exec.shutdown();
    }
    public static void test(NumRangeGenerator gen) {
        test(gen, 10);
    }
}

abstract class NumRangeGenerator {
    private volatile boolean canceled = false;
    public abstract int[] next();
    public void cancel() {
        canceled = true;
    }
    public boolean isCanceled() {
        return canceled;
    }
}
