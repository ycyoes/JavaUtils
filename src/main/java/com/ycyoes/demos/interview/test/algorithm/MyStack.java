package com.ycyoes.demos.interview.test.algorithm;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用两个队列实现栈的先进后出操作，希望该栈的push/pop时间复杂度尽量小
 * 步骤：
 * 1. 若队列q1中只有一个元素，让q1中的元素出队列并输出即可
 * 2. 若队列q1中有多于一个元素，让q1中所有元素出队列，入队列q2,
 *     最后一个元素不如队列q2，输出该元素，然后将队列q2所有元素入队列q1
 * @param <T>
 */
public class MyStack<T> {
    private Queue<T> q1 = new LinkedBlockingQueue<>();
    private Queue<T> q2 = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
    }
    public void push(T val) {
        q1.add(val);
    }
    public boolean empty() {
        if (q1.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public T pop() {
        if (q1.size() == 0) {
            return null;
        } else if (q1.size() == 1) {
            return q1.poll();
        } else {
            while (q1.size() > 1) {
                q2.add(q1.poll());
            }
            T result = q1.poll();
            while (!q2.isEmpty())
                q1.add(q2.poll());
            return result;
        }
    }
}
