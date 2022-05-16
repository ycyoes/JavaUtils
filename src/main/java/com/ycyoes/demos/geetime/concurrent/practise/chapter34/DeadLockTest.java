package com.ycyoes.demos.geetime.concurrent.practise.chapter34;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程死锁
 *
 * 注意： 提交到相同线程池中的任务一定是相互独立的，否则就一定要慎重。
 * @author ycyoes
 * @date 2022-05-16 21:39
 */

public class DeadLockTest {
    public static void main(String[] args) throws Exception {
        //L1/L2阶段共用的线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        //L1阶段的闭锁
        CountDownLatch l1 = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            System.out.println("L1");
            //执行L1阶段任务
            es.execute(() -> {
                //L2阶段的闭锁
                CountDownLatch l2 = new CountDownLatch(2);
                //执行L2阶段子任务
                for (int j = 0; j < 2; j++) {
                    es.execute(() -> {
                        System.out.println("L2");
                        l2.countDown();
                    });
                }
                //等待L2阶段任务执行完
                try {
                    l2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                l1.countDown();
            });
        }
        //等L1阶段任务执行完
        l1.await();
        System.out.println("end");
    }
}
