package com.ycyoes.demos.concurrency.art.chapter8;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger
 * 两个线程通过exchange方法交换数据，如果第一个线程先执行exchange()方法，
 * 它会一直等待第二个线程也执行exchange方法，当两个线程都到达同步点时，这两个线程就可以交换数据，
 * 将本线程生产出来的数据传递给对方。
 * 
 * @author ycyoes
 * @date 2020-02-03 22:51
 */
public class ExchangerTest {
	private static final Exchanger<String> exgr = new Exchanger<String>();
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
	public static void main(String[] args) {
		threadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					String A = "银行流水A";	//A录入银行流水数据
					exgr.exchange(A);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		threadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					String B = "银行流水B";	//B录入银行流水数据
					String A = exgr.exchange(B);
					System.out.println("A和B数据是否一致: " + A.equals(B) + ", A录入的是: " + A + ", B录入的是: " + B);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
