package com.ycyoes.demos.geetime.concurrent.practise.chapter24;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture串行任务执行示例
 *
 * @author ycyoes
 * @date 2022-04-27 21:32
 */
public class SerialTest {
    public static void main(String[] args) {
        /**
         * 首先通过 supplyAsync() 启动一个异步流程，之后是两个串行操作，
         * 整体看起来还是挺简单的。不过，虽然这是一个异步流程，
         * 但任务①②③却是串行执行的，②依赖①的执行结果，③依赖②的执行结果。
         */
        CompletableFuture<String> f0 = CompletableFuture.supplyAsync(
                () -> "Hello World")        //①
                .thenApply(s -> s + " Qq")   //②
                .thenApply(String::toUpperCase); //③
        System.out.println(f0.join());
    }
}
