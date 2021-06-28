package com.ycyoes.utils.test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassTest {
    public static void main(String[] args) {
        Method[] methods = new ClassTest().getMethods();
        Arrays.asList(methods).forEach(System.out::println);
    }

    public Method[] getMethods() {
        return getClass().getMethods();
    }
}
