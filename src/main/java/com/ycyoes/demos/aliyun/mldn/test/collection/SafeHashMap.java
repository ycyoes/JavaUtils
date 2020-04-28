package com.ycyoes.demos.aliyun.mldn.test.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SafeHashMap {
    public static void main(String[] args) {
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
        map.put("test", "test");
        map.put("1", "1");
        System.out.println(map);
    }


}

