package com.ycyoes.demos.aliyun.mldn.test;

import java.util.HashMap;
import java.util.Map;

public class TypeTest {
    public static void main(String[] args) {
        Integer integer = 1;
        int i = integer;

        //自动拆装箱在三目运算符中的使用
        //Boolean默认为null,boolean 默认为false
        Map<String,Boolean> map =  new HashMap<String, Boolean>();
        Boolean b = (map!=null ? map.get("test") : false);
        System.out.println(b);
        System.out.println(map != null);
        Boolean bool = (Boolean)null;
        Boolean bool1 = null;
        System.out.println(bool);
        System.out.println(bool1);
        //使用Boolean默认值判断会报NPE
        if (bool1) {
            System.out.println("A");
        } else {
            System.out.println("B");
        }

        String wechat = "Hollis";
        String introduce = "每日更新Java相关技术文章";
        String hollis = wechat.concat(",").concat(introduce);

    }
}
