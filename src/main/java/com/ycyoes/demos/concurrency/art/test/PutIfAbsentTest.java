package com.ycyoes.demos.concurrency.art.test;

import java.util.concurrent.ConcurrentHashMap;

public class PutIfAbsentTest {
	
	public static void main(String[] args) {
		final ConcurrentHashMap<Object, Object> taskCache = 
				new ConcurrentHashMap<Object, Object>();
		Object obj = taskCache.putIfAbsent("test", "tests");
		System.out.println(obj);
		System.out.println(taskCache.get("test"));
	}
}
