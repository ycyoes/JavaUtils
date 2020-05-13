package com.ycyoes.test.concurrency;

import com.sun.jmx.snmp.ThreadContext;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("test测试");
        System.out.println(threadLocal.get());

    }
}
