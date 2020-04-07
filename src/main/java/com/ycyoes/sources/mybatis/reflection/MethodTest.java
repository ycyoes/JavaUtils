package com.ycyoes.sources.mybatis.reflection;

import java.util.Locale;

public class MethodTest {
    public static void main(String[] args) {
        String name = methodToProperty("Zhangsan");
        System.out.println(name);
    }

    public static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")) {
            name = name.substring(3);
        } else {
            System.out.println("please check your method name. ");
        }
        System.out.println(Character.isUpperCase(name.charAt(1)));

        if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
            name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }

        return name;
    }
}
