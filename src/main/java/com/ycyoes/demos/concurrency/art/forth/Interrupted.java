package com.ycyoes.demos.concurrency.art.forth;

import java.util.concurrent.TimeUnit;

import com.ycyoes.demos.concurrency.art.utils.SleepUtils;

/**
 * 中断
 * 线程通过检查自身是否被中断来进行响应，线程通过方法isInterrupted()来进行判断是否被中断，
 * 也可以调用静态方法Thread.isInterrupted()对当前线程的中断标志位进行复位。
 * 如果该线程已经处于终结状态，即使该线程被中断过，在调用该线程对象的isInterrupted()时依旧会返回false
 * 
 * 许多声明抛出InterruptedException的方法（例如Thread.sleep(long mills)方法），
 * 这些方法在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标识位清除，然后抛出InterruptedException，
 * 此时调用isInterrupted()方法将会返回false
 * @author issuser
 *
 */
public class Interrupted {
	public static void main(String[] args) throws Exception {
		//sleepThread 不停的尝试睡眠
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		//busyThread不停地运行
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);
		sleepThread.start();
		busyThread.start();
		//休眠5秒，让sleepThread和busyThrad充分运行
		TimeUnit.SECONDS.sleep(5);
		sleepThread.interrupt();
		busyThread.interrupt();
		System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
		//防止sleepThread和busyThread立刻退出
		SleepUtils.second(2);
		
	}
	
	static class SleepRunner implements Runnable {
		public void run() {
			while(true) {
				SleepUtils.second(10);
			}
		}
	}
	
	static class BusyRunner implements Runnable {
		public void run() {
			while(true) {
				
			}
		}
	}
}
