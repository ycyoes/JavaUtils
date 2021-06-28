package com.ycyoes.test.reference;

import com.opensource.mybatis.first.User;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args) {
        //通过等号直接建立的引用都是强引用
        User user = new User();

        //通过SoftReference建立的引用是软引用
        SoftReference<User> softRefUser = new SoftReference<>(new User());

        //通过WeakReference建立的引用是弱引用
        WeakReference<User> weakRefUser = new WeakReference<>(new User());
    }
}
