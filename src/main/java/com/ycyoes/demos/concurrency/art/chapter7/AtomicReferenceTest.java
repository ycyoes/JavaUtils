package com.ycyoes.demos.concurrency.art.chapter7;

import java.util.concurrent.atomic.AtomicReference;

import com.ycyoes.demos.concurrency.art.entity.User;

/**
 * 原子更新引用类型
 * @author ycyoes
 * @date 2020-02-02 21:49
 */
public class AtomicReferenceTest {
	public static AtomicReference<User> atomicUserRef = 
			new AtomicReference<User>();
	public static void main(String[] args) {
		User user = new User("conam", 15);
		atomicUserRef.set(user);
		User updateUser = new User("Shinichi", 17);
		atomicUserRef.compareAndSet(user, updateUser);
		System.out.println(atomicUserRef.get().getOld());
	}
}
