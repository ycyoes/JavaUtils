package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.Arrays;
import java.util.List;
//数组转集合后，再进行add/remove/clear操作会报错：java.lang.UnsupportedOperationException
public class ArrayAsList {
    public static void main(String[] args) {
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";
        List<String> list = Arrays.asList(stringArray);
        System.out.println(list);
        list.set(0, "zero");
        System.out.println(stringArray[0]);
        //报错：java.lang.UnsupportedOperationException
//        list.add("four");
//        list.remove(2);
//        list.clear();
        System.out.println(list);
    }
}
