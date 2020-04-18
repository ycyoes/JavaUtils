package com.ycyoes.test.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	static Map<Object, Object> map = new HashMap<Object, Object>();
	public static void main(String[] args) {
		System.out.println(getValue("hello"));
	}
	
	/**
	 * map中不存在相应key值时，返回默认值
	 * @param key
	 * @return
	 */
	public static Object getValue(Object key) {
		return map.computeIfAbsent(key, k -> "default");
	}
}
