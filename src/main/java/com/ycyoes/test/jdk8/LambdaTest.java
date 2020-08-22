package com.ycyoes.test.jdk8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        //1. 构造Stream流的方式
        Stream stream = Stream.of("a", "b", "c");
        String[] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        List<String> streamList = Arrays.asList(strArray);
        stream = streamList.stream();

        //2. Stream流之间的转换

        //3. Stream流的flatMap使用
        //flatMap方法用于映射每个元素到对应的结果，一对多
        String worlds = "The way of the future";
        List<String> list2 = new ArrayList<>();
        list2.add(worlds);
        List<String> list3 = list2.stream().flatMap(str -> Stream.of(str.split(" ")))
                .filter(world -> world.length() > 0).collect(Collectors.toList());
        System.out.println("单词: ");
        list3.forEach(System.out::println);
    }
}
