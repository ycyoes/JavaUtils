package com.ycyoes.test.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPITest {
    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello world");
        }
    }
}
