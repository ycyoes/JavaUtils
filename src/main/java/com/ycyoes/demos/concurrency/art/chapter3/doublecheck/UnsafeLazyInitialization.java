package com.ycyoes.demos.concurrency.art.chapter3.doublecheck;

public class UnsafeLazyInitialization {
	private static Instance instance;
	
	public static Instance getInstance() {
		if (instance == null) {			//1: A线程执行
			instance = new Instance();	//2: B线程执行
		}
		return instance;
	}
	
}
