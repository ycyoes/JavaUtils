package com.ycyoes.demos.aliyun.mldn.test.basic;

public class SuperTest extends Father{
    @Override
    void test() {
        System.out.println("test");
    }
    private SuperTest() {
        super();
    }
    private SuperTest(String name) {
        super.name = name;
    }
}

abstract class Father {
    public String name;
    public Father() {

    }
    private Father(String name){
        this.name = name;
    }
    void print() {
        System.out.println("print");
    }
    abstract void test();
}
