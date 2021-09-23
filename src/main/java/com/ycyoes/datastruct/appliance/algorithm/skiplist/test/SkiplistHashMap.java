package com.ycyoes.datastruct.appliance.algorithm.skiplist.test;

/**
 *
 * 跳表
 *
 * @author ycyoes
 * @create 2021-09-23 19:56
 *
 */
public class SkiplistHashMap {

    /**
     * 链表节点
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K extends Comparable<K>, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
