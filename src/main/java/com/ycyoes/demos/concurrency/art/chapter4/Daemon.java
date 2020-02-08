package com.ycyoes.demos.concurrency.art.chapter4;

import com.ycyoes.demos.concurrency.art.utils.SleepUtils;

/**
 * Daemon线程
 * 在构建Daemon线程时，不能依靠finally块中的内容来确保执行关闭或清理资源的逻辑
 * 
 * @author ycyoes
 *
 */
public class Daemon {
	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
		thread.setDaemon(true);
		thread.start();
	}
	
	static class DaemonRunner implements Runnable {
		public void run() {
			try {
				SleepUtils.second(10);
			} finally {
				System.out.println("DaemonThread finally run.");
			}
		}
	}
}
