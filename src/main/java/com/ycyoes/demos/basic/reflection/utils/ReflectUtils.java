package com.ycyoes.demos.basic.reflection.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.ycyoes.demos.basic.reflection.RichType;

public class ReflectUtils {
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		List<String> list = new ArrayList<String>();
		System.out.println(isCollection(List.class));
		System.out.println(isCollection(list.getClass()));
		
		List<Class<?>> cls = new ArrayList<Class<?>>();
		cls.add(Object.class);
		cls.add(List.class);
		cls.add(SortedSet.class);
		System.out.println(getTypes(cls));
		
		Field fields = RichType.class.getDeclaredField("richField");
		System.out.println(fields);
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
	
	//form class name - 组装类名
	public static String getTypes(List<Class<?>> list) {
		return Optional.ofNullable(list).orElseGet(Collections::emptyList)
		          .stream().map(Class::getSimpleName).collect(Collectors.joining(","));
	}
}
