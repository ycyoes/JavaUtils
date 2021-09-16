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
        if (null == node) {
            DNode<K, V> newNode = new DNode<>(key, value);
            table.put(key, newNode);
            addNode(newNode);

            if (++length > capacity) {
                DNode<K, V> tail = popTail();
            }

        }
    }

    /**
     * 获取节点数据
     *
     * @param key
     * @return
     */
    public V get(K key) {
        DNode<K, V> node = table.get(key);
        if (null == node) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 移除节点数据
     *
     * @param key
     */
    public void remove(K key) {
        DNode<K,V> node = table.get(key);
        if (null == node) {
            return;
        }

        removeNode(node);
        length--;
        table.remove(node.key);
    }

    /**
     * 将节点移动到头部
     * @param node
     */
    private void moveToHead(DNode<K, V> node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * 弹出尾部数据节点
     * @return
     */
    private DNode<K, V> popTail() {
        DNode<K, V> node = tailNode.prev;
        removeNode(node);
        return node;
    }

    /**
     * 移除节点
     *
     * @param node
     */
    private void removeNode(DNode<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将新节点加到头部
     * @param newNode
     */
    private void addNode(DNode<K, V> newNode) {
        newNode.next = headNode.next;
        newNode.prev = headNode;

        headNode.next.prev = newNode;
        headNode.next = newNode;
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
