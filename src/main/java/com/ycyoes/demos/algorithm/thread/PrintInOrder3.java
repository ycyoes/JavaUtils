package com.ycyoes.demos.algorithm.thread;

import java.util.concurrent.Semaphore;

/**
 * LeetCode
 * 1114.按序打印
 * @description 使用Semaphore实现
 * @author ycyoes
 * @date 2020-1-8 21:21
 */
public class PrintInOrder3 {


	public static void main(String[] args) throws Exception {
		Foo3 foo = new Foo3();
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

class Foo3 {
	Semaphore smp1 = new Semaphore(1);
	Semaphore smp2 = new Semaphore(0);
	Semaphore smp3 = new Semaphore(0);
    public Foo3() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
    	smp1.acquire();
		printFirst.run();
		smp2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
    	smp2.acquire();
		printSecond.run();
		smp3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
    	smp3.acquire();
		printThird.run();
    }
}
