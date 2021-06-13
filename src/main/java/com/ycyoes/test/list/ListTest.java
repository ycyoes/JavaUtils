package com.ycyoes.test.list;

import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        int[] a = {1,2,3};
        List list = Arrays.asList(a);
        list.add(2);
        System.out.println(list);
    }
}
