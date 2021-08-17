package com.ycyoes.demos.geetime.algorithm.chapter9;

/**
 * 用数组实现的队列
 *
 * @author ycyoes
 * @date 2021-08-17 18:54
 */
public class ArrayQueue {
    //数组: items, 数组大小: n
    private String[] items;
    private int n = 0;
    //head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    //申请一个大小为capacity的数组
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    //入队
    public boolean enqueue(String item) {
        //如果tail == n 标识队列已经满了
        if(tail == n) return false;
        items[tail] = item;
        ++tail;
        return true;
    }

    //出队
    public String dequeue() {
        //如果head == tail 标识队列为空
        if (head == tail) return null;
        String ret = items[head];
        ++head;
        return ret;
    }
}
