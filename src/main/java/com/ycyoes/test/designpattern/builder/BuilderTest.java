package com.ycyoes.test.designpattern.builder;

import com.opensource.mybatis.first.User;

public class BuilderTest {
    public static void main(String[] args) {
        //用匿名建造者建造一个对象
        User user = new SunnySchoolUserBuilder("Candy").setSex(1).build();
        System.out.println("user: " + user);

        //分布设置建造者属性，建造一个对象
        UserBuilder userBuilder = new SunnySchoolUserBuilder("Eric");
        userBuilder.setEmail("supereric@abc.com").build();
        User user1 = userBuilder.build();
        System.out.println("user1: " + user1);
    }
}
