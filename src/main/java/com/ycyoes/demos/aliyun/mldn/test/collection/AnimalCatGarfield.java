package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.ArrayList;
import java.util.List;

public class AnimalCatGarfield {
    public static void main(String[] args) {
        List<Animal> animal = new ArrayList<>();
        List<Cat> cat = new ArrayList<>();
        List<Garfield> garfield = new ArrayList<>();

        List<? super Cat> superCatFromCat = cat;
        List<? extends Cat> extendsCatFromCat = cat;
//        superCatFromCat.add(new Animal());  //编译错误
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new Garfield());

        //编译错误,除null外不可添加
//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());
        extendsCatFromCat.add(null);
    }
}

class Animal {}
class Cat extends Animal {}
class Garfield extends Cat {}