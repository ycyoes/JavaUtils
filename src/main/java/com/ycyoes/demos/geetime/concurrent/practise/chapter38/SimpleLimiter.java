package com.ycyoes.demos.geetime.concurrent.practise.chapter38;

import java.util.concurrent.TimeUnit;

public class SimpleLimiter {
    //当前令牌桶中的令牌数量
    long storedPermits = 0;
    //令牌桶的容量
    long maxPermits = 3;

    //下一令牌产生时间
    long next = System.nanoTime();
    //发放令牌间隔：纳秒
    long interval = 1000_000_000;

    //请求时间在下一令牌产生时间之后，则
    //1. 重新计算令牌桶中的令牌数
    //2. 将下一个令牌发放时间重置为当前时间
    void resync(long now) {
        if (now > next) {
            //新产生的令牌数
            long newPermits = (now - next) / interval;
            //新令牌增加到令牌桶
            storedPermits = Math.min(maxPermits, storedPermits + newPermits);
            //将下一个令牌发放时间重置为当前时间
            next = now;
        }
    }
    //预占令牌，返回能够获取令牌的时间
    synchronized long reserve(long now) {
        //请求时间在下一令牌产生时间之后
        //重新计算下一令牌产生时间
        if (now > next) {
            //将下一令牌产生时间重置为当前时间
            next = now;
        }
        //能够获取令牌的时间
        long at = next;
        //设置下一令牌产生时间
        next += interval;
        //返回线程需要等待时间
        return Math.max(at, 0L);
    }

    //申请令牌
    void acquire() {
        //申请令牌时的时间
        long now = System.nanoTime();
        //预占令牌
        long at = reserve(now);
        long waitTime = Math.max(at - now, 0);
        //按照条件等待
        if (waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
