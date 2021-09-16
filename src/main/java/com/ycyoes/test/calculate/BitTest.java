package com.ycyoes.test.calculate;

/**
 *
 * 余数计算变为位运算
 * a % b = a & (b - 1)
 *
 * @auth ycyoes
 * @since 2021-09-16
 */
public class BitTest {
    public static void main(String[] args) {
        int a = 5;
        int b = 2;
        System.out.println(a % b);
        System.out.println(a & (b - 1));
    }
}
