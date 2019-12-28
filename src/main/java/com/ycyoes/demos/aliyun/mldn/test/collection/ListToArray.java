package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        //泛型丢失，无法使用String[]接收无参返回结果
        Object[] array1 = list.toArray();
        System.out.println(Arrays.toString(array1));

        //容量不够，则弃用数组
        String[] array2 = new String[2];
        list.toArray(array2);
        System.out.println(Arrays.asList(array2));

        String[] array3 = new String[3];
        list.toArray(array3);
        System.out.println(Arrays.asList(array3));
     }
}
