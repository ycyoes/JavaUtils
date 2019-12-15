package com.ycyoes.demos.thinkinginjava.concurrency.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 若在代码中使用相同的异常处理器，可以在Thread类中设置一个静态域，
 * 并将这个处理器设置为默认的未捕获异常处理器
 * @author ycyoes
 * @since 2019-12-15 13:11
 * @version 1.0
 */
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}
