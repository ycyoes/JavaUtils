package com.ycyoes.demos.thinkinginjava.concurrency.access.atomic;

/**
 * @author ycyoes
 * @since 2019-12-15 17:34
 * @version 1.0
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber() {
        return serialNumber++;  //Not thread-safe
    }
}
