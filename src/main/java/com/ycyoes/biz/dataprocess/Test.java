package com.ycyoes.biz.dataprocess;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String enumName = RelationEnum.getName(1);
        System.out.println(enumName);

        Map map = new HashMap<>();
        System.out.println(map.get("test"));
    }
}
