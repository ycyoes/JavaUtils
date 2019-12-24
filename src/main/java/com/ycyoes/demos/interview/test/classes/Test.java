package com.ycyoes.demos.interview.test.classes;

import java.util.Date;

public class Test extends Date {
    private void test(){
        System.out.println(super.getClass().getName());
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
