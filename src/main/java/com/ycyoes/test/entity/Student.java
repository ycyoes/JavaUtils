package com.ycyoes.test.entity;

import java.util.Comparator;
import java.util.Date;

/**
 * 实体类
 *
 */
public class Student {
    private Long age;
    private String name;
    private Date createDate;

    public Student() {}

    public Student(Long age, String name, Date createDate) {
        this.age = age;
        this.name = name;
        this.createDate = createDate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
