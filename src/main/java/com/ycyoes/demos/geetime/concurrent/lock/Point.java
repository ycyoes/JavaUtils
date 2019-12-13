package com.ycyoes.demos.geetime.concurrent.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock控制锁示例
 * @author ycyoes
 * @since 2019-10-27 20:53
 * @version 1.0
 */

public class Point {
	private double x, y;
	private final StampedLock s1 = new StampedLock();
	
	void move(double deltaX, double deltaY) {
		//获取写锁
		long stamp = s1.writeLock();
		try {
			x += deltaX;
			y += deltaY;
		} finally {
			// 释放写锁
			s1.unlockWrite(stamp);
		}
	}
	
	double distanceFormOrigin() {
		//乐观读操作
		 long stamp = s1.tryOptimisticRead();
		 //拷贝变量
		 double currentX = x, currentY = y;
		 //判断读期间是否有写操作
		 if (!s1.validate(stamp)) {
			//升级为悲观读
			 stamp = s1.readLock();
			 try {
				currentX = x;
				currentY = y;
			} finally {
				s1.unlockRead(stamp);
			}
		}
		 return Math.sqrt(currentX * currentX + currentY * currentY);
	}
}
