package com.ycyoes.demos.geetime.algorithm.chapter18;

/**
 * 散列表
 *
 * @author ycyoes
 * @date 2021-09-07
 */
public class HashTable<K,V> {
    //散列表默认长度
    private static final int DEFAULT_INITAL_CAPACITY = 8;
    //装载因子
    private static final float LOAD_FACTOR = 0.75f;
    //初始化散列表数组
    private Entry<K,V>[] table;
    //实际元素数量
    private int size = 0;
    //散列表索引数量
    private int use = 0;

    public HashTable() {
        table = new Entry[DEFAULT_INITAL_CAPACITY];
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K,V> e = table[index];
        if (e == null || e.next == null) {
            return null;
        }

        while (e.next != null) {
            e = e.next;
            if (key == e.key) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * 新增
     *
     * @param key   键
     * @param value 值
     */
    public void put(K key, V value) {
        int index = hash(key);
        //位置未被引用，创建哨兵节点
        if (table[index] == null) {
            table[index] = new Entry<>(null, null, null);
        }

        Entry<K, V> tmp = table[index];
        //新增节点
        if (tmp.next == null) {
            tmp.next = new Entry<>(key, value, null);
            size++;
            use++;
            //动态扩容
            if (use >= table.length * LOAD_FACTOR) {
                resize();
            }
        } else { //解决散列冲突，使用链表法
            do {
                tmp = tmp.next;
                //key相同，覆盖旧的数据
                if (tmp.key == key) {
                    tmp.value = value;
                    return;
                }
            } while (tmp.next != null);
        }

        Entry<K,V> temp = table[index].next;
        table[index].next = new Entry<>(key, value, temp);
        size++;
    }

    public void remove(K key) {
        int index = hash(key);
        Entry e = table[index];
        if (e == null || e.next == null) {
            return;
        }

        Entry pre;
        Entry<K, V> headNode = table[index];
        do {
            pre = e;
            e = e.next;
            if (key == e.key) {
                pre.next = e.next;
                size--;
                if (headNode.next == null) use--;
                return;
            }
        } while (e.next != null);
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[table.length * 2];
        use = 0;

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null || oldTable[i].next == null) {
                continue;
            }
            Entry<K, V> e = oldTable[i];
            while (e.next != null) {
                e = e.next;
                int index = hash(e.key);
                if (table[index] == null) {
                    use++;
                    //创建哨兵节点
                    table[index] = new Entry<>(null, null, null);
                }
                table[index].next = new Entry<>(e.key, e.value, table[index].next);
            }
        }
    }

    /**
     * 散列函数
     *
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;
    }

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value, Entry<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
