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
        int level;  //层级
        public HeadIndex(Node<T> node, Index<T> down, Index<T> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }

    /**
     * 索引节点：引用着真实节点
     * @param <T>
     */
    private static class Index<T> {
        Node<T> node;   //真实节点
        Index<T> down;  //下指针（第一层的索引实际上是没有下指针的）
        Index<T> right; //右指针

        public Index(Node<T> node, Index<T> down, Index<T> right) {
            this.node = node;
            this.down = down;
            this.right = right;
        }
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


