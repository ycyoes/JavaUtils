package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.ArrayList;
import java.util.List;

public class ListOper {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        for (String item : a) {
            //先删除1，再删除2，会抛出异常：java.util.ConcurrentModificationException
            if ("2".equals(item)) {
                a.remove(item);
            }
        }
        System.out.println(a);

        System.out.println( (int)(7.0 / 0.75F + 1.0F));
    }
}
