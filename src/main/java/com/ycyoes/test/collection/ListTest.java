package com.ycyoes.test.collection;

import com.ycyoes.test.entity.Student;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List集合测试
 *
 * @author ycyoes
 * @date 2020-04-10
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("2");
        list.add("1");
        list.add("6");
        list.add("4");
        List<String> subList = list.subList(0, 2);
        subList.stream().forEach(System.out::println);
        System.out.println("------排序---------");
        List<String> list1 = list.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
        list1.stream().forEach(System.out::println);
        Student s1 = new Student(21L, "Jeccia", Date.from(LocalDateTime.now().minusMonths(5).atZone(ZoneId.systemDefault()).toInstant()));
        Student s2 = new Student(23L, "Jason", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        Student s3 = new Student(22L, "Lucy", Date.from(LocalDateTime.now().plusMonths(2).atZone(ZoneId.systemDefault()).toInstant()));
        Student s4 = new Student(22L, "David", Date.from(LocalDateTime.now().plusMonths(3).atZone(ZoneId.systemDefault()).toInstant()));
        List<Student> ls = new ArrayList<>();
        ls.add(s1);
        ls.add(s2);
        ls.add(s3);
        ls.add(s4);
        ls.stream().forEach(System.out::println);
        List<Student> ls2 = ls.stream().sorted((stu1, stu2) ->
                stu2.getAge().compareTo(stu1.getAge())
        ).collect(Collectors.toList());
        System.out.println("--------sorted student-------");
        ls2.stream().forEach(System.out::println);

        System.out.println("-------multi field----------");
//        List<Student> ls3 = ls.stream().sorted(Comparator.comparing(Student::getCreateDate).reversed().thenComparing(Student::getAge)).collect(Collectors.toList());
//        ls3.stream().forEach(System.out::println);

        Comparator<Student> bya = Comparator.comparing(Student::getAge);//按照a升序
        Comparator<Student> byb = Comparator.comparing(Student::getCreateDate).reversed();//按照b升序
        Collections.sort(ls, bya.thenComparing(byb));
        ls.stream().forEach(System.out::println);

        List<Object> list2 = new ArrayList<>();
        list2.add("");
        list2.add("");
        System.out.println(list2.size());
    }
}
