package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.TreeMap;
//1. 在Key有排序要求的场景下使用TreeMap可以事半功倍
//2. TreeMap并非一定要覆写hashCode和equals方法来达到Key去重的目的
//3. TreeMap是线程不安全的集合，不能在多线程之间进行共享数据的写操作。
// 在多线程进行写操作时，需要添加互斥机制，或者把对象放在Collections.synchronizedMap(TreeMap)中实现同步
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(55, "fifty-five");
        treeMap.put(56, "fifty-six");
        treeMap.put(57, "fifty-seven");
        treeMap.put(58, "fifty-eight");
        treeMap.put(83, "eight-three");
        treeMap.remove(57);
        treeMap.put(59, "fifty-nine");
        System.out.println(treeMap);
    }
}
