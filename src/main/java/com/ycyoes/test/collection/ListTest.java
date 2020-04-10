package com.ycyoes.test.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * List集合测试
 *
 * @author ycyoes
 * @date 2020-04-10
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<String> subList = list.subList(0, 2);
        subList.stream().forEach(System.out::println);
    }
}
