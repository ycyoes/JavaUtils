package com.ycyoes.demos.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * 
 * @author ycyoes
 * @date 2020-03-30 23:34
 * @param <T>
 */
public class LargestSubSeq3 {
	public static void main(String[] args) {
		String s = "dvdf";
		System.out.println(s.charAt(1));
		System.out.println(lengthOfLongestSubstring(s));
	}
	
	public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
		int len = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0,j = 0; i < n; i++) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(map.get(s.charAt(i)), j);
			}
			len = Math.max(len, i - j + 1);
			map.put(s.charAt(i), i + 1);
		}
		return len;
	}

}
