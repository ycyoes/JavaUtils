package com.ycyoes.demos.basic.concurrency.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String> future = (Future<String>) executor.submit(() -> {
			Thread.sleep(3000);
			return "异步任务计算结果!";
		});
		
		//提交完异步任务后，主线程可以继续干一些其他的事情
		doSomeThingElse();
		
		
		//为了获取异步计算结果，我们可以通过future.get和轮询机制来获取
		String result;
		
		//get方式会导致当前线程阻塞，这显然违背了异步计算的初衷
		//result = future.get()
		
		//轮询方式虽然不会导致当前线程阻塞，但是会导致高额的CPU负载
		long start = System.currentTimeMillis();
		while(true) {
			if (future.isDone()) {
				break;
			}
		}
		System.out.println("轮询耗时：" + (System.currentTimeMillis() - start));
		
		result = future.get();
		System.out.println("获取到异步执行结果: " + result);
		
		executor.shutdown();
	}
	
	private static void doSomeThingElse() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("my work is done.");
	}
}
