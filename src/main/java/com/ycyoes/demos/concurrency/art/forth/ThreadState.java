package com.ycyoes.demos.concurrency.art.forth;

import com.ycyoes.demos.concurrency.art.utils.SleepUtils;

/**
 * 线程状态
 * 线程在自身的生命周期中，并不是固定的处于某个状态，而是随着代码的执行在不同的状态之间进行切换
 * @author ycyoes
 *
 */
public class ThreadState {
	public static void main(String[] args) {
		new Thread(new TimeWaiting(), "TimeWaitingThread").start();
		new Thread(new Waiting(), "WaitingThread").start();
		//使用两个Blocked线程，一个获取锁成功，另一个被阻塞
		new Thread(new Blocked(), "BlockedThread-1").start();
		new Thread(new Blocked(), "BlockedThread-2").start();
	}
	
	//该线程不断的进行睡眠
	static class TimeWaiting implements Runnable {
		public void run() {
			while (true) {
				SleepUtils.second(100);
			}
		}
	}
	
	//该线程在Waiting.class实例上等待
	static class Waiting implements Runnable {
		public void run() {
			while(true) {
				synchronized (Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	//该线程在Blocked.class实例上加锁后，不会释放该锁
	static class Blocked implements Runnable {
		public void run() {
			synchronized (Blocked.class) {
				while(true) {
					SleepUtils.second(100);
				}
			}
		}
	}
}
