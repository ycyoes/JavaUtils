package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.ArrayList;
import java.util.List;

public class AnimalCatGarfield {
    public static void main(String[] args) {
        List<Animal> animal = new ArrayList<>();
        List<Cat> cat = new ArrayList<>();
        List<Garfield> garfield = new ArrayList<>();

        List<? super Cat> superCat = new ArrayList<>();
        List<? extends Cat> extendsCat = new ArrayList<>();
        List<? super Cat> superCatFromCat = cat;

        List<? extends Cat> extendsCatFromCat = cat;

//        superCatFromCat.add(new Animal());  //编译错误
        superCatFromCat.add(new Cat());

        superCatFromCat.add(new Garfield());

        //List<? super Cat>只能添加Cat及其子类
        superCat.add(new Cat());
        superCat.add(new Garfield());
//        superCat.add(new Animal());   //编译错误

//        extendsCat.add(new Animal());
//        extendsCat.add(new Cat());
//        extendsCat.add(new Garfield());

        /*List<?> tlist = cat;
        System.out.println(tlist.size());*/

        //编译错误,除null外不可添加
//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());
        extendsCatFromCat.add(null);

//        List<?>为通配符集合，可以接受任何类型的集合引用赋值，
//        不能添加任何元素，但是可以remove和clear,并非immutable集合
        //List<?>一般作为参数来接收外部的集合，或者返回一个不知道具体元素类型的集合
        List<?> tlist = cat;
        System.out.println(tlist.size());
    }
}

class Animal {}
class Cat extends Animal {}
class Garfield extends Cat {}
class SCat extends Cat {}