package com.ycyoes.demos.geetime.concurrent.practise.chapter24;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture异常处理
 *
 * @author ycyoes
 * @date 2022-04-27 21:39
 */
public class CompletableFutureException {
    public static void main(String[] args) {
        CompletableFuture<Integer> f0 = CompletableFuture.supplyAsync(() -> (7/0))
                .thenApply(r -> r*10)
//                .exceptionally(e -> 0);
                .exceptionally(e -> {
                    System.out.println(e);
                    return null;
                });
        System.out.println(f0.join());
    }

}
