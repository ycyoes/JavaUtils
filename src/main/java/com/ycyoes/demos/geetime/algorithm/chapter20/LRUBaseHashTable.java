package com.ycyoes.demos.geetime.algorithm.chapter20;

import java.util.HashMap;

/**
 *
 * 基于散列表的LRU算法
 *
 * @author ycyoes
 * @create 2021-09-16 19:56
 *
 */
public class LRUBaseHashTable<K,V> {

    private final static Integer DEFAULT_CAPACITY = 10; //默认链表容量
    private DNode<K,V> headNode;    //头节点
    private DNode<K,V> tailNode;    //尾节点
    private Integer length; //链表长度
    private Integer capacity;   //链表容量
    private HashMap<K, DNode<K, V>> table;

    public LRUBaseHashTable(int capacity) {
        this.length = 0;
        this.capacity = capacity;
        headNode = new DNode<>();
        tailNode = new DNode<>();

        headNode.next = tailNode;
        tailNode.prev = headNode;

        table = new HashMap<>();
    }

    public LRUBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 新增
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        DNode<K, V> node = table.get(key);
    }

    //双向链表
    static class DNode<K, V> {
        private K key;
        private V value;    //数据
        private DNode<K, V> prev;   //前驱指针
        private DNode<K, V> next;   //后继指针

        DNode() {}

        DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
