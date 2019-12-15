package com.ycyoes.demos.thinkinginjava.concurrency.access.practise;
/** Create a class containing two data fields, and a method that manipulates
 * those fields in a multistep process so that, during the execution of that
 * method, those fields are in an "improper state" (according to some definition
 * that you establish). Add methods to read the fields, and create multiple
 * threads to call the various methods and show that the data is visible in its
 * "improper state." Fix the problem using the synchronized keyword.
 **/

import java.util.Random;

/**
 * @author ycyoes
 * @since 2019-12-15 16:20
 * @version 1.0
 */

//Synchronized version that runs without error
public class SynchronizedNumRangeGenerator11 extends NumRangeGenerator{
    private int min = 0;
    private int max = 0;
    private int[] range = {min, max};
    private Random rand = new Random();
    public synchronized int[] next() {
        min = rand.nextInt(100);
        max = rand.nextInt(100);
        Thread.yield();
        if (min > max) max = min;
        int[] ia = {min, max};
        return ia;
    }

    public static void main(String[] args) {
        NumRangeCheck11.test(new SynchronizedNumRangeGenerator11());
    }
}


