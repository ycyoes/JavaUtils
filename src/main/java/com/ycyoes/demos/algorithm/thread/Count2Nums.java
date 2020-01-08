package com.ycyoes.demos.algorithm.thread;

import java.util.Arrays;

/**
 * LeetCode1.两数之和
 * @author ycyoes
 * @date 2020-1-8 21:34
 */
public class Count2Nums {
	
	public static void main(String[] args) {
//		int[] nums = new int[] {3,3};
		int[] nums = new int[] {2, 7, 11, 15};
		int target = 7;
		int[] ret = new Solution().twoSum(nums, target);
		System.out.println(Arrays.toString(ret));
	}
}

class Solution {
	 public int[] twoSum(int[] nums, int target) {
	    	int[] ret = new int[2];
	        for (int i = 0; i < nums.length; i++) {
	        	int fir = nums[i];
				for (int j = i + 1; j < nums.length; j++) {
					if (fir + nums[j] == target) {
						ret[0] = i;
						ret[1] = j;
	                    return ret;
					}
				}
			}
	        return ret;
	    
	    }
}