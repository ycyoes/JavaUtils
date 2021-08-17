package com.ycyoes.demos.geetime.algorithm.chapter9;

/**
 * 基于链表实现的队列
 *
 * @author ycyoes
 * @date 2021-08-17 19:39
 */
public class LinkedListQueue {
    //队列的队首和队尾
    private Node head = null;
    private Node tail = null;

    //入队
    public void enqueue(String value) {
        if (tail == null) {
            Node newNode = new Node(value, null);
            head = newNode;
            tail = newNode;
        } else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
    }

    public String dequeue() {
        if (head == null) return null;

        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public void printAll() {
        Node p = head;
        while(p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static class Node {
        private Node next;
        private String data;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }
}
