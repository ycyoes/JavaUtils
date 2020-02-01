package com.ycyoes.demos.concurrency.art.utils;

import java.util.concurrent.TimeUnit;

/**
 * sleep 指定时间
 * @author ycyoes
 *
 */
public class SleepUtils {
	public static final void second(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
