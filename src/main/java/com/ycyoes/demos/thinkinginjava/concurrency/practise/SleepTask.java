package com.ycyoes.demos.thinkinginjava.concurrency.practise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Create a task that sleeps for a random amount of time between 1 and 10 seconds,
// then displays its sleep time and exits. Create and run a quantity (given on the
// command line) of these tasks.

public class SleepTask {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new SleepRandomTime());
        exec.shutdown();
    }
}

class SleepRandomTime implements Runnable {
    Random random = new Random();
    public void run() {
        int st = 1000 * random.nextInt(10);
        try {
            TimeUnit.MILLISECONDS.sleep(st);
            System.out.println("sleep " + st + " millseconds.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
