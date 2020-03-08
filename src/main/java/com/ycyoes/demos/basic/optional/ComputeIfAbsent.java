package com.ycyoes.demos.basic.optional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Optional -> ComputeIfAbsent
 * 
 * @since 2020-03-08
 * @author ycyoes
 *
 */
public class ComputeIfAbsent {
	public static void main(String[] args) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		/**
	       * 以下写法等价于:
	       * List<Method> list = conflictingMethods.get(name);
	       * if(list == null) {
	       *     list = new ArrayList<>();
	       *     conflictingMethods.put(name, list);
	       * }
	       * 写法简便，且可避免NPE
	       */
		List<String> list = map.computeIfAbsent("test", key -> new ArrayList<>());
		list.add("tests");
		System.out.println(list.get(0));
	}
}
