package com.ycyoes.demos.concurrency.art.chapter3.doublecheck;

public class SafeDoubleCheckLocking {
	private volatile static Instance instance;
	public static Instance getInstance() {
		if (instance == null) {
			synchronized (SafeDoubleCheckLocking.class) {
				if (instance == null) {
					instance = new Instance();	//instance为volatile,现在没问题了
				}
			}
		}
		return instance;
	}
}
