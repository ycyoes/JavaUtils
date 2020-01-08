package com.ycyoes.demos.algorithm.thread;

import java.util.concurrent.CountDownLatch;

/**
 * LeetCode
 * 1114.按序打印
 * @author ycyoes
 *
 */
public class PrintInOrder {
	public static void main(String[] args) throws Exception {
		Foo foo = new Foo();
		foo.first(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("first");
			}
		});
		foo.first(new Runnable() {
					
					@Override
					public void run() {
						System.out.println("two");
					}
				});
		foo.first(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("three");
			}
		});
	}
}

class Foo {
	private CountDownLatch cdl1 = new CountDownLatch(1);
	private CountDownLatch cdl2 = new CountDownLatch(1);
    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        cdl1.countDown();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        cdl1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        cdl2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        cdl2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
