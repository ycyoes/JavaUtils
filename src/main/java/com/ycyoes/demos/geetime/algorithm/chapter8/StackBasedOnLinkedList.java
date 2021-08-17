package com.ycyoes.demos.geetime.algorithm.chapter8;

import java.util.LinkedList;
import java.util.List;

/**
 * 基于链表实现的顺序栈
 *
 * @author ycyoes
 * @date 2021-08-17 18:23
 */
public class StackBasedOnLinkedList {
    List list = new LinkedList();
    //入栈操作
    public boolean push(String item) {
        list.add(item);
        return true;
    }

    //出栈操作
    public Object pop() {
        return list.get(list.size() - 1);
    }


}
