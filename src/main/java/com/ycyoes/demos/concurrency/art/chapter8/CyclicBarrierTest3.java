package com.ycyoes.demos.concurrency.art.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * isBroken()方法用来了解阻塞的线程是否被中断
 * 
 * @author ycyoes
 * @date 2020-02-03 22:19
 */
public class CyclicBarrierTest3 {
	static CyclicBarrier c = new CyclicBarrier(2);
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread.interrupt();
		try {
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(c.isBroken());
		}
	}
}
