package com.ycyoes.datastruct.appliance.algorithm.skiplist.test;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * 跳表
 *
 * @author ycyoes
 * @create 2021-09-23 19:56
 *
 */
public class SkiplistHashMap {


    static class SkiplistNode<K extends Comparable<K>, V>  {
        int hash;
        K key;
        V value;
        int maxLevel;
        Node<K, V>[] nexts;

        //往跳表中添加一个元素（只有头节点可调用此方法）
        private V putValue(int hash, K key, V value) {
            //1. 算出层数
            int level = randomLevel();
            //2. 如果层数高出头节点层数，则增加头节点层数
            if (level > maxLevel) {
                level = ++maxLevel;
                SkiplistNode<K, V>[] oldNexts = this.nexts;
                SkiplistNode<K, V>[] newNexts = new SkiplistNode[level];
                for (int i = 0; i < oldNexts.length; i++) {
                    newNexts[i] = oldNexts[i];
                }
//                this.nexts = newNexts;
            }
            return null;
        }

        private int randomLevel() {
            int level = 1;
            int random = ThreadLocalRandom.current().nextInt();
            while (((random >>>= 1) & 1) != 0) {
                level++;
            }
            return level;
        }

        public V findValue(K key) {
            int level = this.maxLevel;
            SkiplistNode<K, V> q = this;
            int c;
            //i-- 控制向下
            for (int i = (level - 1); i >= 0; i--) {
                while (q.nexts[i] != null && (c = q.nexts[i].key.compareTo(key)) <= 0) {
                    if (c == 0) {
                        //找到了返回
                        return q.nexts[i].value;
                    }
                    //控制向右
                    q = q.nexts[i];
                }
            }
            return null;
        }
    }

    /**
     * 链表节点
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K extends Comparable<K>, V> extends SkiplistNode<K, V> {
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
