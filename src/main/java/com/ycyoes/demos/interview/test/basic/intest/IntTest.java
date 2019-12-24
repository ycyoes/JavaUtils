package com.ycyoes.demos.interview.test.basic.intest;

public class IntTest {
    public static void main(String[] args) {
        //在数字中可以添加分隔符，下划线只能用在数字中间，编译的时候下划线会被编译器去掉，
        //这样做的好处是：避免了一些难以通过观察代码来发现的细微错误。
        int a = 100_234;
        System.out.println("a: " + a);
    }
}
