package com.ycyoes.demos.interview.test.basic.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTest {
	public static void main(String[] args) {
		//因为他们比较的不是对象中字段的值或者说本身对象的值，比较的是物理地址，所以结果都为false
		Object obj = new Object();
		Object obj1 = new Object();
		System.out.println(obj.equals(obj1));
		System.out.println(obj == obj1);
		System.out.println(obj.toString());
		System.out.println(obj1.toString());
		String xmlInput = "hello:world";
		int key = xmlInput.indexOf(":");
		System.out.println(key);
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "first");
		map.put("2", "second");
		map.put("3", "third");
		String[] keys = map.keySet().toArray(new String[map.size()]);
		System.out.println(Arrays.asList(keys));
		
		Map<String, List<String>> mapList = new HashMap<String, List<String>>();
		List<String> list = mapList.get("4");
		list.add("hello");
		System.out.println(list);
	}
}
