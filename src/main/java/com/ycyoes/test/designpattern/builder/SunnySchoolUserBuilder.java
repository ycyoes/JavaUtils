package com.ycyoes.test.designpattern.builder;

import com.opensource.mybatis.first.User;

public class SunnySchoolUserBuilder implements UserBuilder {
    private String name;
    private String email;
    private Integer age;
    private Integer sex;
    private String schoolName;

    public SunnySchoolUserBuilder(String name) {
        this.name = name;
    }

    @Override
    public SunnySchoolUserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public SunnySchoolUserBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public SunnySchoolUserBuilder setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public User build() {
        if(this.name != null && this.email == null) {
            this.email = this.name.toLowerCase().replace(" ", "").concat("@sunnyschllo.com");
        }
        if (this.age == null) {
            this.age = 7;
        }
        if (this.sex == null) {
            this.sex = 0;
        }
        this.schoolName = "Sunny School";
        return new User(name, email, age, sex, schoolName);
    }

    @Override
    public String toString() {
        return "SunnySchoolUserBuilder{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
