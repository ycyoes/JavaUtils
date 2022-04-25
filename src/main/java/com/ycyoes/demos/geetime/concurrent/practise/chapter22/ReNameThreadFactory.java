package com.ycyoes.demos.geetime.concurrent.practise.chapter22;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ReNameThreadFactory implements ThreadFactory {
    /**
     * 线程池编号（static修饰）(容器里面所有线程池的数量)
     */
    private static final AtomicInteger POOLNUMBER = new AtomicInteger(1);

    /**
     * 线程编号(当前线程池线程的数量)
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    //线程组
    private final ThreadGroup group;

    //业务名称前缀
    private final String namePrefix;

    /**
     * 重写线程名称（获取线程池编号，线程编号，线程组）
     *
     * @param prefix 你需要指定的业务名称
     */
    public ReNameThreadFactory(@NotNull String prefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup());
        //组装线程前缀
        namePrefix = prefix + "-poolNumber:" + POOLNUMBER;
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                //方便dump的时候排查（重写线程名称）
                namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
