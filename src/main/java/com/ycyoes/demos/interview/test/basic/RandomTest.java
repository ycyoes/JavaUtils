package com.ycyoes.demos.interview.test.basic;

import java.util.Random;

/**
 * 1. Math类属于java.lang包下
 * 2. Math.random()输出[0, 1)
 * 3. new Random().nextInt(11)输出[0, 10]或者[0, 11)
 */
public class RandomTest {
    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int res = rand.nextInt();
//            System.out.println("res: " + res);
            System.out.println(Math.random());
        }
    }
}
