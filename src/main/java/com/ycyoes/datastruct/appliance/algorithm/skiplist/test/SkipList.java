package com.ycyoes.datastruct.appliance.algorithm.skiplist.test;

/**
 *
 * 跳表
 *
 * @author ycyoes
 * @create 2021-09-18 19:56
 *
 */
public class SkipList {

    private static class HeadIndex<T> extends Index<T> {

    }


    /**
     * 索引节点：引用这真实节点
     * @param <T>
     */
    static class Node<T> {
        T value;    //节点元素值
        Node<T> next;   //下一个节点

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public String toString() {
            return (value == null ? "h0" : value.toString()) + "->" + (next == null ? "null" : next.toString());
        }
    }
}


