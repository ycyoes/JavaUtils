package com.ycyoes.test.spi;

import java.util.List;

public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyWord) {
        System.out.println("文件搜索：" + keyWord);
        return null;
    }
}
