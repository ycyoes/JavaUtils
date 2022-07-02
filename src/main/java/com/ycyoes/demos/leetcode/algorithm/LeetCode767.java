package com.ycyoes.demos.leetcode.algorithm;

public class LeetCode767 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String s = "aabcdcaefg";
//        String s = "aabcdaaa";
        String res = reStructString(s, s.length());
//        System.out.println("output str: " + res);
        long end = System.currentTimeMillis();
        System.out.println("执行时间： " + (end - start)/1000 + "秒");
    }

    public static String reStructString(String inputStr, int length) {
        //把字符串S转化为字符数组
        char[] alphabetArr = inputStr.toCharArray();
        //记录每个字符出现的次数
        int[] alphabetCount = new int[26];
        //统计每个字符出现的次数
        for (int i = 0; i < length; i++) {
            alphabetCount[alphabetArr[i] - 'a']++;
        }
        int max = 0, alphabet = 0, threshold = (length + 1) >> 1;
        //找出出现次数最多的那个字符
        for (int i = 0; i < alphabetCount.length; i++) {
            if (alphabetCount[i] > max) {
                max = alphabetCount[i];
                alphabet = i;
                //如果出现次数最多的那个字符的数量大于阈值，说明他不能使得
                // 两相邻的字符不同，直接返回空字符串即可
                if (max > threshold) {
                    System.out.println("no");
                    return "";
                }
            }
        }
        //到这一步说明他可以使得两相邻的字符不同，我们随便返回一个结果，res就是返回
        //结果的数组形式，最后会再转化为字符串的
        char[] res = new char[length];
        int index = 0;
        //先把出现次数最多的字符存储在数组下标为偶数的位置上
        while (alphabetCount[alphabet]-- > 0) {
            res[index] = (char) (alphabet + 'a');
            index += 2;
        }
        //然后再把剩下的字符存储在其他位置上
        for (int i = 0; i < alphabetCount.length; i++) {
            while (alphabetCount[i]-- > 0) {
                if (index >= res.length) {
                    index = 1;
                }
                res[index] = (char) (i + 'a');
                index += 2;
            }
        }

        if (res != null && res.length > 0) {
            System.out.println("yes");
            System.out.println(res);
        }
        return new String(res);
    }
}
