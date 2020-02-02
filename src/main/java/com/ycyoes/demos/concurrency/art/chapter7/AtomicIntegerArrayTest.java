package com.ycyoes.demos.concurrency.art.chapter7;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组
 * @author ycyoes
 * @date 2020-02-02 21:45
 */
public class AtomicIntegerArrayTest {
	static int[] value = new int[] {1, 2};
	static AtomicIntegerArray ai = new AtomicIntegerArray(value);
	public static void main(String[] args) {
		ai.getAndSet(0, 3);
		System.out.println(ai.get(0));
		System.out.println(value[0]);
	}
}
