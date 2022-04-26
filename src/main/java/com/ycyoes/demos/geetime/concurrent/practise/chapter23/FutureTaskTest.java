package com.ycyoes.demos.geetime.concurrent.practise.chapter23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask用法示例
 *
 * @author ycyoes
 * @date 2022-04-26 21:14
 */
public class FutureTaskTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        executorServiceExe();
        threadExe();
    }

    public static void threadExe() throws ExecutionException, InterruptedException {
        //创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1+2);
        //创建并启动线程
        Thread t1 = new Thread(futureTask);
        t1.start();
        //获取计算结果
        Integer result = futureTask.get();
        System.out.println(result);
    }


    public static void executorServiceExe() throws ExecutionException, InterruptedException {
        //创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1+2);
        //创建线程池
        ExecutorService es = Executors.newCachedThreadPool();
        //提交FutureTask
        es.submit(futureTask);
        //获取计算结果
        Integer result = futureTask.get();
        System.out.println(result);
    }
}
