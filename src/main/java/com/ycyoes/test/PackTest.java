package com.ycyoes.test;

/**
 *
 * 包装类型
 *
 * @auth ycyoes
 * @since 2020-04-07
 */
public class PackTest {
    public static void main(String[] args) {
        //包装类型初始值为null
        Long l = 1L;
        System.out.println(l != null);
        Long l1 = null;
        System.out.println(l1 != null);
        Long l2 = l1;
        System.out.println(l2);
    }
}
