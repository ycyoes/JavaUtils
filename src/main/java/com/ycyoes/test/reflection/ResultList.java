package com.ycyoes.test.reflection;

public class ResultList {
	public static void main(String[] args) {
		Class cls = convertNull2Class();
		System.out.println(cls);
	}
	
	public static <T> T convertNull2Class() {
		return (T)null;
	}
}


