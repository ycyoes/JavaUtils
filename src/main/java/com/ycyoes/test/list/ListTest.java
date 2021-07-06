package com.ycyoes.test.list;

import com.ycyoes.demos.geetime.algorithm.chapter5.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        System.out.println("whether is empty or not: " + list.isEmpty());
        list.add(1);
        list.add(2);
        Arrays.asList(list).forEach(System.out::println);
        Object a = list.remove(0);
        System.out.println("element of deleted: " + a);
        list.add(3);
        list.add("test");
        Arrays.asList(list).forEach(System.out::println);

        list.remove("test");
        Arrays.asList(list).forEach(System.out::println);

    }
}
