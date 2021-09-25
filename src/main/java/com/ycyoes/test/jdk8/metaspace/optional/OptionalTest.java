package com.ycyoes.test.jdk8.metaspace.optional;

import com.opensource.mybatis.first.User;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        User user = null;
        user = Optional.ofNullable(user).orElse(new User());
    }
}
