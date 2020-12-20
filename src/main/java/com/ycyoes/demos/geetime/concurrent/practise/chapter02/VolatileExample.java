package com.ycyoes.demos.geetime.concurrent.practise.chapter02;

/**
 * 程序的顺序性规则
 * @author ycyoes
 * @date 2020-12-19
 */
public class VolatileExample {
    int x = 0;
    volatile boolean v = false;

    public static void main(String[] args) {
        VolatileExample ve = new VolatileExample();
        ve.writer();
        ve.reader();
    }

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v == true) { // 这里x会是多少呢？
            System.out.println("x: " + x);
        }
    }

}