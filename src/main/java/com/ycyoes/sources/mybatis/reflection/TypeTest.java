package com.ycyoes.sources.mybatis.reflection;

import com.ycyoes.demos.basic.reflection.DiyVideoYoutubeAuth;
import com.ycyoes.demos.basic.reflection.RichType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java类型判断
 *
 * @author ycyoes
 * @since 2020-04-02
 */
public class TypeTest {

    public static void main(String[] args) {

        Field[] fields = DiyVideoYoutubeAuth.class.getDeclaredFields();
        Arrays.stream(fields).forEach(System.out::println);
    }


}
