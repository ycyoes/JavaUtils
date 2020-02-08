package com.ycyoes.demos.concurrency.art.chapter3.volatileexp;

public class VolatileFeaturesExample {
	long vl = 0L;	//64位的long型普通变量
	
	public synchronized void set(long l) {
		vl = l;		//对单个的普通变量的写用同一个锁同步
	}
	
	public void getAndIncrement() {	//普通方法调用
		long temp = get();			//调用已同步的读方法
		temp += 1L;					//普通写操作
		set(temp);					//调用已同步的写方法
	}
	
	//对单个的普通变量的读用同一个锁同步
	public synchronized long get() {
		return vl;	
	}
}
