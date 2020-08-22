package com.ycyoes.test.jdk8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        System.out.println("map普通遍历方式： ");
        for(String key : map.keySet()) {
            System.out.println("key = " + key + ", value = " + map.get(key));
        }

        System.out.println("map lambda表达式遍历： ");
        map.forEach((k, v) -> {
            System.out.println("k = " + k + " v = " + v);
        });

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("bb");
        list.add("ccc");
        list.add("dddd");
        System.out.println("list-lambda表达式遍历： ");
        list.forEach(v -> {
            System.out.println(v);
        });

        System.out.println("list-lambda表达式双冒号遍历： ");
        list.forEach(System.out::println);
    }
}
