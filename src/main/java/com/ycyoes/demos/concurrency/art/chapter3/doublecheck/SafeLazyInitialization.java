package com.ycyoes.demos.concurrency.art.chapter3.doublecheck;

public class SafeLazyInitialization {
private static Instance instance;
	
	public synchronized static Instance getInstance() {
		if (instance == null) {			
			instance = new Instance();	
		}
		return instance;
	} 
}
