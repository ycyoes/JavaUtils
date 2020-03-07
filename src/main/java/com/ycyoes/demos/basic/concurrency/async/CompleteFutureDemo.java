package com.ycyoes.demos.basic.concurrency.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteFutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFutureOne = new CompletableFuture<String>();
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(() -> {
			try {
				Thread.sleep(3000);
				completableFutureOne.complete("异步任务执行结果");
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		//WhenComplete方法返回的CompletableFuture仍然是原来的CompletableFuture计算结果
		CompletableFuture<String> completableFutureTwo = completableFutureOne.whenComplete((s, throwable) -> {
			System.out.println("当异步任务执行完毕时打印异步任务的执行结果: " + s);
		});
		
		//ThenApply方法返回的是一个新的completeFuture
		CompletableFuture<Integer> completableFutureThree = completableFutureTwo.thenApply(s -> {
			System.out.println("当异步任务执行结束时， 根据上一次的异步任务结果, 继续开始一个新的异步任务!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s.length();
		});
		
		System.out.println("阻塞方式获取执行结果: " + completableFutureThree.get());
		
		executor.shutdown();
	}
}
