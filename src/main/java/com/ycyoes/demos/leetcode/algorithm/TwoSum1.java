package com.ycyoes.demos.leetcode.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1:
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author ycyoes
 * @create 2020-03-29 18:56
 *
 */
public class TwoSum1 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 13;
        int[] res = twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.containsKey(val)) {
                return new int[]{map.get(val), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
