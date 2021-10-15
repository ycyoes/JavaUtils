package com.ycyoes.test.generic.inter;

public class GenericsInterfaceDemo02<T> implements Content<T> {
    private T text;

    public GenericsInterfaceDemo02(T text) {
        this.text = text;
    }

    @Override
    public T text() {
        return text;
    }

    public static void main(String[] args) {
        GenericsInterfaceDemo02<String> gen = new GenericsInterfaceDemo02<>("ABC");
        System.out.println(gen.text);
    }
}
