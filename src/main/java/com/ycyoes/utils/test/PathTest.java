package com.ycyoes.utils.test;

import org.apache.ibatis.io.VFS;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PathTest {
    public static void main(String[] args) throws IOException {
//        String path = "E:\\project\\xx\\docs";
        String path = "E:/project/xx/docs";
        List list = Collections.list(Thread.currentThread().getContextClassLoader().getResources(path));
        System.out.println(list.size());
        list.forEach(l -> System.out.println(l));
        List<String> l1 = VFS.getInstance().list(path);
        System.out.println(l1.size());
    }
}
