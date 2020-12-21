package com.ycyoes.demos.geetime.concurrent.practise.chapter06;

import java.util.ArrayList;
import java.util.List;

/**
 * 等待-通知
 * @author ycyoes
 * @date 2020-12-22 00:14
 */
public class Allocator {
    private List<Object> als;
    //一次性申请所有资源
    synchronized void apply(Object from, Object to) {
        //经典写法
        //只包含一个，需要等待；若都不包含或都包含则不需等待
        while(als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        als.add(from);
        als.add(to);
    }

    //归还资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("2");
//        list.add("3");
        System.out.println(list.contains("2"));
        System.out.println(list.contains("2") || list.contains("3"));
        System.out.println(!(list.contains("2") || list.contains("3")));
        System.out.println(list.contains("2") && list.contains("3"));

    }
}
