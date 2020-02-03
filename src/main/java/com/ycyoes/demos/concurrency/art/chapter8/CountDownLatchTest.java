package com.ycyoes.demos.concurrency.art.chapter8;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch允许一个或多个线程等待其他线程完成操作
 * @author ycyoes
 * @date 2020-02-03 21:47
 */
public class CountDownLatchTest {
	static CountDownLatch c = new CountDownLatch(2);
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(1);
				c.countDown();
				System.out.println(2);
				c.countDown();
			}
		}).start();
		c.await();
		System.out.println(3);
	}
	
}
