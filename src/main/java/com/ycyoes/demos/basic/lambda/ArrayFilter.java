package com.ycyoes.demos.basic.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 找出所有 长度>=5的字符串，并且忽略大小写、去除重复字符串，然后按字母排序，最后用“爱心❤”连接成一个字符串输出！
 * @author ycyoes
 *
 */
public class ArrayFilter {
	static String[] str = new String[] {"1","2","bilibili","of","codesheep","5","at","BILIBILI","codesheep","23","CHEERS","6"};
	public static void main(String[] args) {
		String list = Arrays.stream(str).filter(i -> i.length() > 5)
				.map(i -> i.toLowerCase())
				.distinct()
				.sorted(Comparator.naturalOrder())
				.collect(Collectors.joining("❤"));
		System.out.println(list);
	}
}
