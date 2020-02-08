package com.ycyoes.demos.concurrency.art.chapter3.volatileexp;

public class VolatileBarrierExample {
	int a;
	volatile int v1 = 1;
	volatile int v2 = 2;
	
	void readAndWrite() {
		int i = v1;		//第一个volatile读
		int j = v2;		//第二个volatile读	
		a = i + j;		//普通写
		v1 = i + 1;		//第一个volatile写
		v2 = j * 2;		//第一个volatile写
	}
}
