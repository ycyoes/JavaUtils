package com.ycyoes.demos.algorithm.thread;

import java.util.concurrent.CountDownLatch;

/**
 * LeetCode
 * 1114.按序打印
 * @description 使用CountDownLatch实现
 * @author ycyoes
 *
 */
public class PrintInOrder {
	public static void main(String[] args) throws Exception {
		Foo foo = new Foo();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					foo.first(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("first");
							
						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					foo.second(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("second");
						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					foo.third(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("three");
						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t2.start();
		t1.start();
		t3.start();
		
	}
}

class Foo {
	private CountDownLatch cdl1 = new CountDownLatch(1);
	private CountDownLatch cdl2 = new CountDownLatch(1);
    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        cdl1.countDown();
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
