package com.ycyoes.demos.thinkinginjava.concurrency.access.atomic;

/**
 * @author ycyoes
 * @since 2019-12-15 17:17
 * @version 1.0
 */
public class Atomicity {
    int i;
    void f1() { i++; }
    void f2() { i += 3; }
}
