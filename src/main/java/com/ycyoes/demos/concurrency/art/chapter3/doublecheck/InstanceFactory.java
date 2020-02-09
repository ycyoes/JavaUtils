package com.ycyoes.demos.concurrency.art.chapter3.doublecheck;

public class InstanceFactory {
	private static class InstanceHolder {
		public static Instance instance = new Instance();
	}
	
	public static Instance getInstance() {
		return InstanceHolder.instance; 	//这里导致InstanceHolder初始化
	}
}
