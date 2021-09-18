package com.ycyoes.datastruct.appliance.algorithm.skiplist.test;

import java.util.Comparator;

/**
 *
 * 跳表
 *
 * @author ycyoes
 * @create 2021-09-18 19:56
 *
 */
public class SkipList<T> {
    Comparator comparator;
    Index<T> head;
    public static void main(String[] args) {
    }

    /**
     * 查找元素
     * 先找到前置索引节点，再往后查找
     *
     * @param value
     * @return
     */
    public T get(T value) {
        if (value == null) {
            throw new NullPointerException();
        }

        Comparator<T> cmp = this.comparator;
        //第一大步：先找到前置的索引节点
        Node<T> preIndexNode = findPreIndexNode(value, true);
        //如果要查找的值正好是索引节点
        if (preIndexNode.value != null && cmp.compare(preIndexNode.value, value) == 0) {
            return value;
        }

        //第二大步：再按链表的方式查找
        Node<T> q;
        Node<T> n;
        int c;
        for (q = preIndexNode;;) {
            n = q.next;
            c = cmp.compare(n.value, value);
            if (c == 0) return value;   //找到了
            if (c > 0) return null; //没找到
            q = n;  //下一个
        }
    }

    /**
     *
     * @param value 要查找的值
     * @param contain   是否包含value的索引
     * @return
     */
    private Node<T> findPreIndexNode(T value, boolean contain) {
        /*
         * q---->r---->r
         * |     |
         * |     |
         * v     v
         * d     d
         * q = query
         * r = right
         * d = down
         */
        // 从头节点开始查找，规律是先往右再往下，再往右再往下
        Index<T> q = this.head;
        Index<T> r, d;
        Comparator<T> cmp = this.comparator;
        for (;;) {
            r = q.right;
            if (r != null) {
                //包含value的索引，正好有
                if (contain && cmp.compare(r.node.value, value) == 0) {
                    return r.node;
                }

                //如果右边的节点比value小，则右移
                if (cmp.compare(r.node.value, value) < 0) {
                    q = r;
                    continue;
                }
            }
            d = q.down;
            //如果下面的索引为空了，则返回该节点
            if (d == null) {
                return q.node;
            }
            //否则，下移
            q = d;
        }

    }

    /**
     * 头节点：标记层
     * @param <T>
     */
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
     * 链表中的节点:真正存数据的节点
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


