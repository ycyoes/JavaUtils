package com.ycyoes.test.generic.inter;

public class GenericsExtendsDemo01 {
    static <T extends Comparable<T>> T max(T x, T y, T z) {
        T max = x;  //假设x是初始最大值
        if (y.compareTo(max) > 0) {
            max = y;    //y更大
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }

    public static <T> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for(T e : anArray) {
//            if (e > elem) //compile error
                ++count;
        }
        return count;
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(max(3, 4, 5));
        System.out.println(max(6.6, 8.8, 7.7));
        System.out.println(max("pear", "apple", "orange"));
    }
}
