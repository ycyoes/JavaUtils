package com.ycyoes.demos.test.text;

public class Test {
    public static void main(String[] args) {
        String str = "updateHomeShowVideoId\t,\t0\t,\t0";
        String[] ss = str.split(",");
        String first = ss[1].trim();
        System.out.println(first + "1");
        System.out.println("0".equals(first));
    }
}
