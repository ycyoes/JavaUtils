package com.ycyoes.demos.thinkinginjava.concurrency.executor;

import com.ycyoes.demos.thinkinginjava.concurrency.task.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果向SingleThreadExecutor提交了多个任务，那么这些任务将排队，每个任务都会在
 * 下一个任务开始之前运行结束，所有任务使用相同的线程。
 * SingleThreadExecutor会序列化所有提交给它的任务，并会维护它自己(隐藏)的悬挂任务队列。
 */

/**
 * Adding more threads.
 * @author ycyoes
 * @since 2019-12-13 11:51
 * @version 1.0
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}
