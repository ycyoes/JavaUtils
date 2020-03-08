package com.ycyoes.demos.basic.reflection.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectUtils {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		System.out.println(isCollection(List.class));
		System.out.println(isCollection(list.getClass()));
	}

	/**
	 * 判断是否为集合
	 * A.class.isAssignableFrom(B.class) -> A是否为B的父类或父接口
	 * @param <T>
	 * @param type
	 * @return
	 */
	public static <T> boolean isCollection(Class<T> type) {
	    return Collection.class.isAssignableFrom(type);
	  }
}
