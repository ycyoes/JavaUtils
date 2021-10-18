package com.ycyoes.test.spi;

import java.util.List;

public class DatabaseSearch implements Search {
    @Override
    public List<String> searchDoc(String keyWord) {
        System.out.println("数据搜索： " + keyWord);
        return null;
    }
}
