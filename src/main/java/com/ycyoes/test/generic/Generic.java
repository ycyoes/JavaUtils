package com.ycyoes.test.generic;

import java.util.ArrayList;
import java.util.Arrays;

public class Generic {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        //类型擦除
        //可通过反射添加其他类型元素，
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        Arrays.asList(list).forEach(System.out::println);
    }
}
