package com.ycyoes.demos.geetime.concurrent.practise.chapter23;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MakeTea {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务T2的FutureTask
        FutureTask ft2 = new FutureTask(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask ft1 = new FutureTask(new T1Task(ft2));
        //线程T1执行任务ft1
        Thread t1 = new Thread(ft1);
        t1.start();
        //线程T2执行任务ft2
        Thread t2 = new Thread(ft2);
        t2.start();
        //等待线程T1执行结果
        System.out.println(ft1.get());

    }


    // T1Task需要执行的任务：
    // 洗水壶、烧开水、泡茶
    static class T1Task implements Callable<String> {
        FutureTask<String> ft2;
        T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1:烧开水...");
            TimeUnit.SECONDS.sleep(15);

            //获取T2线程的茶叶
            String tf = ft2.get();
            System.out.println("T1:拿到茶叶：" + tf);

            System.out.println("T1:泡茶...");

            return "上茶： " + tf;
        }
    }

    // T2Task需要执行的任务：
    // 洗茶壶、洗茶杯、拿茶叶
    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            //获取T2线程的茶叶
            System.out.println("T2:拿茶叶");
            return "龙井";
        }
    }

}
