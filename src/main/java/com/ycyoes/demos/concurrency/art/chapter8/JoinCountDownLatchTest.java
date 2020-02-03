package com.ycyoes.demos.concurrency.art.chapter8;


/**
 * JoinCountDownLatchTest-通过join实现
 * @author ycyoes
 * @date 2020-02-03 17:32
 */
public class JoinCountDownLatchTest {
	public static void main(String[] args) throws InterruptedException {
		Thread parser1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("parser1 finish");
			}
		});
		
		Thread parser2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("parser2 finish");
			}
		});
		
		parser1.start();
		parser2.start();
		
		//join用于让当前执行线程等待join线程执行结束。其实现原理是不停检查join线程是否存活，
		//如果join线程存活则让当前线程永远等待。其中，wait(0)表示永远等待下去。
		parser1.join();
		parser2.join();
		System.out.println("all parser finish");
		
	}
}
