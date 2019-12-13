package com.ycyoes.demos.thinkinginjava.concurrency.executor.practise;

import java.util.ArrayList;
import java.util.concurrent.*;
//the task is a Callable that sums the values of all
// the Fibonacci numbers. Create several tasks and display the results.

public class Example5 {
    public static void main(String[] args) {
        /*ExecutorService exec = Executors.newCachedThreadPool();
        Future<Integer> f = exec.submit(new Ex2FibonacciD(12));
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }*/
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
        for(int i = 0; i < 20; i++)
            results.add(exec.submit(new Ex2FibonacciD(i)));
        for(Future<Integer> fs : results)
            try {
                // get() blocks until completion:
                System.out.println(fs.get());
            } catch(InterruptedException e) {
                System.out.println(e);
                return;
            } catch(ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
    }
}

class Ex2FibonacciD implements Callable<Integer> {
    private int n = 0;
    private int count = 0;
    public Ex2FibonacciD(int n) {
        this.n = n;
    }
    private int fib(int x) {
        if (x < 2) return 1;
        return fib(x - 2) + fib(x - 1);
    }
    public Integer call() {
        for (int i = 0; i < n; i++)
            count += fib(i);
        return count;
    }
}
