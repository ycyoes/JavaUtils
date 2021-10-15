package com.ycyoes.test.generic.inter;

public class GenericsInterfaceDemo implements Content<Integer> {
    private int text;

    public GenericsInterfaceDemo(int text) {
        this.text = text;
    }

    @Override
    public Integer text() {
        return text;
    }

    public static void main(String[] args) {
        GenericsInterfaceDemo demo = new GenericsInterfaceDemo(10);
        System.out.println(demo.text);
    }
}
