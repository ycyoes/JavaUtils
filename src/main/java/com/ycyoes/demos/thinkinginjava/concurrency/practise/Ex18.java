package com.ycyoes.demos.thinkinginjava.concurrency.practise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/** Create a non-task class with a method that calls sleep() for a long
 * interval. Create a task that calls the method in the non-task class.
 * In main(), start the task, then call interrupt() to teminate it. Make
 * sure that the task shuts down safely.
 **/
public class Ex18 {
    public static void main(String[] args) {
        //3 ways to do it:
        //use Thread:
        Thread t = new Thread(new Worker());
        t.start();
        t.interrupt();
        //use Executor execute:
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new Worker());
        exec.shutdownNow();
        //use Executor submit:
        ExecutorService exec2 = Executors.newSingleThreadExecutor();
        Future<?> f = exec2.submit(new Worker());
        try {
            TimeUnit.MILLISECONDS.sleep(100);   //start task
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted in main");
        }
        f.cancel(true);
        exec2.shutdown();
    }
}

class Worker implements Runnable {
    public void run(){
        Nontask.rest();
    }
}

class Nontask {
    public static void rest(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        } finally {
            System.out.println("Good bye");
        }
    }
}
