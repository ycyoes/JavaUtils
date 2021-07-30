package com.ycyoes.demos.geetime.algorithm.chapter8;

/**
 * 基于数组实现的顺序栈
 *
 * @author ycyoes
 * @date 2021-07-30 11:23
 */
public class ArrayStack {
    private String[] items; //数组
    private int count;  //栈中元素个数
    private int n;  //栈的大小

    //初始化数组，申请一个大小为n的数组空间
    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    //入栈操作


}
