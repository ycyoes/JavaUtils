package com.ycyoes.test.designpattern.builder;


import com.opensource.mybatis.first.User;

public interface UserBuilder {
    public UserBuilder setEmail(String email);
    public UserBuilder setAge(Integer age);
    public UserBuilder setSex(Integer sex);

    public User build();
}
