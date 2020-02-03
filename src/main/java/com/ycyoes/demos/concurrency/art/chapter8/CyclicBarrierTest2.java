package com.ycyoes.demos.concurrency.art.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 构造函数CyclicBarrier（int parties，Runnable barrier-Action），
 * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
 * 
 * @author ycyoes
 * @date 2020-02-03 22:19
 */
public class CyclicBarrierTest2 {
	static CyclicBarrier c = new CyclicBarrier(2, new A());
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					c.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(1);
			}
		}).start();
		try {
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(2);
	}
	
	static class A implements Runnable {
		public void run() {
			System.out.println(3);
		}
	}
}
