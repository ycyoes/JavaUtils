package com.ycyoes.demos.algorithm.thread;

/**
 * LeetCode
 * 1114.按序打印
 * @description 使用synchronized-wait-notifyall实现
 * @author ycyoes
 * @date 2020-1-8 20:40
 */
public class PrintInOrder2 {

	public static void main(String[] args) throws Exception {
		Foo1 foo = new Foo1();
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

class Foo1 {
	private byte[] lock = new byte[0];
	private volatile boolean firstFinished =false;
	private volatile boolean secondFinished =false;
    public Foo1() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
    	synchronized (lock) {
    		printFirst.run();
    		firstFinished = true;
    		lock.notifyAll();
		}
    }

    public void second(Runnable printSecond) throws InterruptedException {
    		synchronized (lock) {
    			while(!firstFinished) {
        			lock.wait();
        		}
        		printSecond.run();
        		secondFinished = true;
        		lock.notifyAll();
			}
    }

    public void third(Runnable printThird) throws InterruptedException {
    		synchronized (lock) {
    			while(!secondFinished) {
        			lock.wait();
        		}
        		printThird.run();
			}
    }
}

