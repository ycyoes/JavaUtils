package com.ycyoes.demos.geetime.concurrent.practise.chapter34;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintAsync {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
//        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() -> {
            try {
                String qq = pool.submit(() -> "QQ").get();
                System.out.println(qq);
            } catch (Exception e) {

            }
        });
    }
}
