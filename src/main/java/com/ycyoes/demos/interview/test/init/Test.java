package com.ycyoes.demos.interview.test.init;

import java.lang.reflect.Field;

//初始化顺序
public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Base b = new Sub();
        System.out.println(b.num);
        ReadOnlyClass roc = new ReadOnlyClass();
        
    }
}

class ReadOnlyClass {
    private Integer age = 20;
    public Integer getAge() { return age; }
}

class Sub extends Base{
    int num = 3;
    public Sub() {
        this.print();
        num = 4;
    }
    public void print() {
        System.out.println("Sub.num = " + num);
    }
}

class Base {
    int num = 1;
    public Base() {
        this.print();
        num = 2;
    }
    public void print() {
        System.out.println("Base.num = " + num);
    }
}
