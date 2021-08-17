package com.ycyoes.demos.geetime.algorithm.chapter8;

/**
 * 基于链表实现的顺序栈
 *
 * @author ycyoes
 * @date 2021-08-17 18:23
 */
public class StackBasedOnLinkedList {
    private Node top = null;

    //入栈操作
    public void push(int value) {
        Node newNode = new Node(value, null);
        //判断是否栈空
        if(top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    //出栈操作
    public Object pop() {
        if (top == null) return -1;
        int value = top.data;
        top = top.next;
        return value;
    }

    public void printAll() {
        Node p = top;
        while(p != null) {
            System.out.println(p.data + "");
            p = p.next;
        }
        System.out.println();
    }


    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
