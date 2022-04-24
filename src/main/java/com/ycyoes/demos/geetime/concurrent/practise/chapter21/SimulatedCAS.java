package com.ycyoes.demos.geetime.concurrent.practise.chapter21;

public class SimulatedCAS {
    volatile int count;
    int newValue;

    //实现count+=1
    void addOne() {
        do {
            newValue = count + 1;
        } while(count != cas(count, newValue));
    }

    //模拟实现CAS，仅用来帮助理解
    synchronized int cas(int expect, int newValue) {
        //读目前的count值
        int curValue = count;
        //比较目前count值是否==期望值
        if (curValue == expect) {
            //如果是，则更新count的值
            count = newValue;
        }
        //返回写入前的值
        return curValue;
    }
}
