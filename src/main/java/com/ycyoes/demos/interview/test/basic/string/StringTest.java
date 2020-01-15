package com.ycyoes.demos.interview.test.basic.string;

public class StringTest {
	public static void main(String[] args) {
		//因为他们比较的不是对象中字段的值或者说本身对象的值，比较的是物理地址，所以结果都为false
		Object obj = new Object();
		Object obj1 = new Object();
		System.out.println(obj.equals(obj1));
		System.out.println(obj == obj1);
		
		System.out.println(obj.toString());
		System.out.println(obj1.toString());
	}
}
